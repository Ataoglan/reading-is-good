package com.getir.readingisgood.domain.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddBookRequest {
    private String bookName;
    private String author;
    private double price;
    private int stock;
}
