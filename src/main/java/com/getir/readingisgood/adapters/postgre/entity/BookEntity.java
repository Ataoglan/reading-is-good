package com.getir.readingisgood.adapters.postgre.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class BookEntity extends BaseEntity {

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Column
    private String author;

    @Column
    private int stock;

    @Column
    private double price;


}