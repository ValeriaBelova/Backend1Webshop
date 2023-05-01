package com.example.backend1webshop.Webshop.repos;

import com.example.backend1webshop.Webshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
