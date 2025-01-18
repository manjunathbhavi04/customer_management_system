package com.example.Customer.Relationship.Manager.controller;

import com.example.Customer.Relationship.Manager.model.Customer;
import com.example.Customer.Relationship.Manager.repository.CustomerRepository;
import com.example.Customer.Relationship.Manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers") //default end point and initial endpoint
public class CustomerController {
    @Autowired
    private CustomerService customService;
    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer newCustomer = customService.createCustomer(customer);
//        Customer newCustomer = customerRepo.save(customer);
        System.out.println(HttpStatus.CREATED);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestParam long customerId, @RequestBody Customer cust){
        Customer customer =  customService.updateCustomer(customerId, cust);

        Customer updatedCustomer = customService.createCustomer(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

    }

    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(@RequestParam long customerId){
        return new ResponseEntity<>(customService.deleteCustomer(customerId), HttpStatus.NO_CONTENT);
    }
}
