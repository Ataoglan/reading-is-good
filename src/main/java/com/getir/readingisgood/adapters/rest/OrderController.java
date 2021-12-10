package com.getir.readingisgood.adapters.rest;

import com.getir.readingisgood.application.service.OrderService;
import com.getir.readingisgood.domain.ApiResponse;
import com.getir.readingisgood.domain.order.CreateOrderRequest;
import com.getir.readingisgood.domain.order.GetOrderByDateRequest;
import com.getir.readingisgood.domain.order.GetOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("secure/order/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("v1/createOrder")
    private void createOrder(@RequestBody @Validated CreateOrderRequest createOrderRequest){
        orderService.createOrder(createOrderRequest);
    }

    @GetMapping("v1/getOrder")
    private ApiResponse getOrder(@RequestBody @Validated GetOrderRequest getOrderRequest){
        return ApiResponse.success(orderService.getOrder(getOrderRequest));
    }

}
