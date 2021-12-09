package com.getir.readingisgood.domain.order;

import com.getir.readingisgood.adapters.postgre.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateOrderRequest {
    private long userId;
    private List<BookEntity> book;
    private double totalPrice;
    private int totalAmount;
}
