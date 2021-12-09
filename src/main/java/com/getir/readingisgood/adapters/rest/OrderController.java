package com.getir.readingisgood.adapters.rest;

import com.getir.readingisgood.application.service.OrderService;
import com.getir.readingisgood.domain.ApiResponse;
import com.getir.readingisgood.domain.order.CreateOrderRequest;
import com.getir.readingisgood.domain.order.GetOrderByDateRequest;
import com.getir.readingisgood.domain.order.GetOrderRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("secure/order/")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("v1/createOrder")
    private ApiResponse createOrder(@RequestBody @Validated CreateOrderRequest createOrderRequest){
        return ApiResponse.success(orderService.createOrder(createOrderRequest));
    }

    @GetMapping("v1/getOrder")
    private ApiResponse getOrder(@RequestBody @Validated GetOrderRequest getOrderRequest){
        return ApiResponse.success(orderService.getOrder(getOrderRequest));
    }

    @GetMapping("v1/getOrderByDate")
    private ApiResponse getOrderByDate(@RequestBody @Validated GetOrderByDateRequest getOrderByDateRequest){
        return ApiResponse.success(orderService.getOrderByDate(getOrderByDateRequest));
    }
}
