package com.example.backend1webshop.Webshop.controllers;

import com.example.backend1webshop.Webshop.models.Product;
import com.example.backend1webshop.Webshop.repos.ProductRepo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductRepo repo;

    public ProductController(ProductRepo repo) {
        this.repo = repo;
    }

    @RequestMapping("items")
    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    @RequestMapping("/items/{id}")
    public Optional<Product> itemsById(@PathVariable Long id){

        return repo.findById(id);
    }
}
