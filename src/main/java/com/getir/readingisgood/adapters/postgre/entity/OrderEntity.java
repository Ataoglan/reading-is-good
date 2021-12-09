package com.getir.readingisgood.adapters.postgre.entity;

import com.getir.readingisgood.application.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

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
    private int quantity;

    @Column
    @ManyToMany
    private List<BookEntity> book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
