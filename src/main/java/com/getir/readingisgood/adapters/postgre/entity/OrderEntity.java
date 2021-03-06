package com.getir.readingisgood.adapters.postgre.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    @Column
    private double totalPrice;

    @Column
    private int totalQuantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
