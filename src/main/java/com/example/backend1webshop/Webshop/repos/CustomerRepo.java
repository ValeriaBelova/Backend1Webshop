package com.example.backend1webshop.Webshop.repos;
import com.example.backend1webshop.Webshop.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
