package com.getir.readingisgood.adapters.rest;

import com.getir.readingisgood.application.service.UserService;
import com.getir.readingisgood.domain.ApiResponse;
import com.getir.readingisgood.domain.login.LoginRequest;
import com.getir.readingisgood.domain.login.SignupRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/auth/")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("v1/signup")
    private ApiResponse userSignup(@RequestBody @Validated SignupRequest signupRequest) {
        return ApiResponse.success(userService.signUp(signupRequest));
    }

    @PostMapping("v1/signin")
    private ApiResponse userSignin(@RequestBody @Validated LoginRequest loginRequest){
        return ApiResponse.success(userService.signIn(loginRequest));
    }
}
