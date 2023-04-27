package com.example.backend1webshop.Webshop.repos;

import com.example.backend1webshop.Webshop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
