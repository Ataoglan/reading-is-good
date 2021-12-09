package com.getir.readingisgood.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderByDateRequest {
    private Date startDate;
    private Date endDate;
}
