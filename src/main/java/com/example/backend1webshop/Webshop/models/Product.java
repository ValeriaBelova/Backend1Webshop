package com.example.backend1webshop.Webshop.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;


    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
