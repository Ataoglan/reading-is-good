package com.getir.readingisgood.adapters.postgre.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsEntity extends BaseEntity{
    @Column
    private int quantity;

    @Column
    private double unitPrice;

    @Column
    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    @Column
    private long bookId;

}
