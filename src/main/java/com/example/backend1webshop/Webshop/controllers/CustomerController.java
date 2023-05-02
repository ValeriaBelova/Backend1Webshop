package com.example.backend1webshop.Webshop.controllers;

import com.example.backend1webshop.Webshop.models.Customer;
import com.example.backend1webshop.Webshop.repos.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepo customerRepo;
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @RequestMapping("/customers")
    public List<Customer> getAllCustomers() {
        log.info("All customers are listed here");
        return customerRepo.findAll();
    }

    @RequestMapping("/customers/{id}")
    public Customer getById2(@PathVariable Long id){
        if(customerRepo.findById(id).isPresent()){
            log.info("Customer found with id "+id);
            return customerRepo.findById(id).get();
        }else{
            log.info("Customer with id "+id+ " not found");
            return null;
        }
    }

    /*@PostMapping("/customers/add")
    public String addCustomer(@RequestBody Customer c) {
        customerRepo.save(c);
        log.info("New customer added");
        return "Added";
    }*/


    @PostMapping("customers/add")
    public String addCustomer2(@RequestBody Customer c){
        String name = c.getName();
        String ssn = c.getSsn();

        if (name != null && ssn != null){
            if(! name.isEmpty() && ! ssn.isEmpty()){
                customerRepo.save(c);
                log.info("New customer "+name+ " was added");
                return "New customer " +c.getName()+ " was added";
            }
        }
        log.info("New customer was not added");
        return "Could not save customer. Make sure that customer name and ssn are present";

    }

}
