package com.getir.readingisgood.adapters.rest;

import com.getir.readingisgood.application.service.UserService;
import com.getir.readingisgood.domain.ApiResponse;
import com.getir.readingisgood.domain.user.GetCustomerOrderRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secure/customer/")
public class CustomerController {
    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("v1/getCustomerOrders")
    private ApiResponse getCustomerOrders(@RequestBody @Validated GetCustomerOrderRequest getCustomerOrderRequest){
        return ApiResponse.success(userService.getCustomerOrders(getCustomerOrderRequest));
    }

}
