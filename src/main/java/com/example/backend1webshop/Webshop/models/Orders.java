package com.example.backend1webshop.Webshop.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime date;

    @OneToOne
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<Product> products;

    public Orders(LocalDateTime date, Customer customer, List<Product> products) {
        this.date = date;
        this.customer = customer;
        this.products = products;
        for (Product product : products) {
            product.setOrders(this);
        }

    }
}
