package com.getir.readingisgood.domain.book;

import com.getir.readingisgood.adapters.postgre.enums.UpdateStockEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStockRequest {
    private UpdateStockEnum stockType;
    private int quantity;
    private String bookName;
    private String author;
}
