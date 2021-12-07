package com.getir.readingisgood.adapters.postgre.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statistics")
public class StatisticsEntity extends BaseEntity{

    @Column
    private int totalOrderCount;

    @Column
    private int totalBookCount;

    @Column
    private double totalPurchasedAmount;
}
