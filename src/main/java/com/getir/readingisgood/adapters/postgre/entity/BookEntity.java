package com.getir.readingisgood.adapters.postgre.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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

    @Version
    private Integer version;

    @Column
    private double price;

}
