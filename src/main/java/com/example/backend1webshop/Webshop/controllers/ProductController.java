package com.example.backend1webshop.Webshop.controllers;

import com.example.backend1webshop.Webshop.models.Product;
import com.example.backend1webshop.Webshop.repos.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepo repo;


    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    public ProductController(ProductRepo repo) {
        this.repo = repo;
    }

    @RequestMapping("items")
    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    @RequestMapping("/items/{id}")
    public Product itemsById(@PathVariable Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            log.info("No item with that id exists");
            return null;
        }
    }
    @PostMapping("items/add")
    public String addProduct(@RequestBody Product p){
        try {
            repo.save(p);
            log.info("Product saved");
            return "Saved";
        } catch(DataAccessException e){
                log.error("Failed to save product", e);
                return "Failed to save product";
            }
    }

}
