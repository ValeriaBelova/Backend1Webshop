package com.example.backend1webshop.Webshop.controllers;

import com.example.backend1webshop.Webshop.models.Orders;
import com.example.backend1webshop.Webshop.repos.CustomerRepo;
import com.example.backend1webshop.Webshop.repos.OrdersRepo;
import com.example.backend1webshop.Webshop.repos.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class OrderController {
    private final OrdersRepo repo;
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    OrderController(OrdersRepo repo){
        this.repo = repo;
    }

    @RequestMapping("/orders")
    public List<Orders> getAllOrders(){
        List<Orders> listOfOrders = repo.findAll();
        if(listOfOrders.isEmpty()){
            log.warn("The list of orders was empty");
        }
        return repo.findAll();
    }

    @RequestMapping("/orders/{customerId}")
    public List<Orders> getOrdersByCustomerId(@PathVariable Long customerId){
        List<Orders> listOfOrders = repo.findAll();
        return listOfOrders.stream()
                .filter(orders -> Objects.equals(orders.getCustomer().getId(), customerId))
                .toList();
    }
}
