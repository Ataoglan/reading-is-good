package com.getir.readingisgood.application.service;

import com.getir.readingisgood.adapters.postgre.entity.OrderEntity;
import com.getir.readingisgood.adapters.postgre.entity.UserEntity;
import com.getir.readingisgood.adapters.postgre.repository.OrderRepository;
import com.getir.readingisgood.adapters.postgre.repository.UserRepository;
import com.getir.readingisgood.application.service.component.JwtTokenUtil;
import com.getir.readingisgood.domain.login.LoginRequest;
import com.getir.readingisgood.domain.login.LoginResponse;
import com.getir.readingisgood.domain.login.SignupRequest;
import com.getir.readingisgood.domain.login.SignupResponse;
import com.getir.readingisgood.domain.user.GetCustomerOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final JwtTokenUtil jwtTokenUtil;
    private final OrderRepository orderRepository;


    public SignupResponse signUp(SignupRequest signupRequest) {

        userRepository.findByEmail(signupRequest.getEmail()).ifPresent(
                userEntity -> {throw new RuntimeException("email is already exists!");});

        UserEntity userEntity = UserEntity.builder()
                .nameSurname(signupRequest.getNameSurname())
                .password(signupRequest.getPassword())
                .email(signupRequest.getEmail())
                .address(signupRequest.getAddress())
                .build();

        userRepository.save(userEntity);

        return new SignupResponse(true);

    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);

        if (!optionalUserEntity.isPresent())
            throw new RuntimeException("invalid username or password");
        UserEntity userEntity = optionalUserEntity.get();
        UserDetails userDetail = User.withUsername(userEntity.getEmail()).password(userEntity.getPassword()).build();

        return userDetail;
    }

    public LoginResponse signIn(LoginRequest signinRequest) {
        try {
            UserDetails userDetails = loadUserByUsername(signinRequest.getEmail());
         //   authenticationService.authenticate(signinRequest.getEmail(), signinRequest.getPassword());
            if (!userDetails.getPassword().equals(signinRequest.getPassword())){
                throw new RuntimeException("invalid username or password");
            }
            return new LoginResponse(jwtTokenUtil.generateToken(userDetails), jwtTokenUtil.generateRefreshToken(userDetails));
        } catch (Exception e) {
            throw new RuntimeException("invalid username or password");
        }
    }

    public List<OrderEntity> getCustomerOrders(GetCustomerOrderRequest getCustomerOrderRequest) {
        return userRepository.findById(getCustomerOrderRequest.getCustomerId()).map(user->
             orderRepository.findOrderEntityByUser(user)
                    .stream().sorted(Comparator.comparing(OrderEntity::getCreatedDate))
                    .collect(Collectors.toList())
        ).orElseThrow(RuntimeException::new);
    }
}
