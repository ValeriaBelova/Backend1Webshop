package com.example.backend1webshop.Webshop.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String ssn;

    // if you delete a Customer entity, JPA will also delete all their associated Orders entities.
    // Similarly, if you persist a new Customer entity,
    // JPA will also persist any associated Orders entities.
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> orders;


    public Customer(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }
}
