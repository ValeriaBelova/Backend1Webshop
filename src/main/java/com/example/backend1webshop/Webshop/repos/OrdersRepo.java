package com.example.backend1webshop.Webshop.repos;

import com.example.backend1webshop.Webshop.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
    List<Orders> findOrdersByCustomer_Id(Long customerId);

}
