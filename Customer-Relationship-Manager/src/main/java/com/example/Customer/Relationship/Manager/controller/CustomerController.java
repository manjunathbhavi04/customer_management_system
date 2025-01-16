package com.example.Customer.Relationship.Manager.controller;

import com.example.Customer.Relationship.Manager.model.Customer;
import com.example.Customer.Relationship.Manager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer newCustomer = customerRepo.save(customer);
        System.out.println(HttpStatus.CREATED);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestParam long customerId, @RequestBody Customer cust){
        Customer customer =  customerRepo.findById(customerId).orElseThrow(() -> new ResourceAccessException("Customer not found with Id: "+ customerId));
        customer.setFirstName(cust.getFirstName());
        customer.setLastName(cust.getLastName());
        customer.setEmail(cust.getEmail());

        Customer updatedCustomer = customerRepo.save(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

    }

    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(@RequestParam long customerId){
        customerRepo.deleteById(customerId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.NO_CONTENT);
    }
}
