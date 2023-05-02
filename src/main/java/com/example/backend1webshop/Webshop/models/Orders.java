package com.example.backend1webshop.Webshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn
    private Customer customer;
    @ManyToMany
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Orders(LocalDateTime date, Customer customer, List<Product> products) {
        this.date = date;
        this.customer = customer;
        this.products = products;
    }
}
