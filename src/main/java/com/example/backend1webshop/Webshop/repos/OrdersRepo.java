package com.example.backend1webshop.Webshop.repos;

import com.example.backend1webshop.Webshop.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<Orders, Long> {
}
