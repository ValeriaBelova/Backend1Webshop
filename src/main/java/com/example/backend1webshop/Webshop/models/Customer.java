package com.example.backend1webshop.Webshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String ssn;

    public Customer(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }
}
