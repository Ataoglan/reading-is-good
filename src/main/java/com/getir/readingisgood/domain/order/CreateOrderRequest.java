package com.getir.readingisgood.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class CreateOrderRequest {
    private Map<Long, Integer> bookAndQuantity;
}
