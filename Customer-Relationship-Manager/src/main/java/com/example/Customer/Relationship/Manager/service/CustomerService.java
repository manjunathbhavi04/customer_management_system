package com.example.Customer.Relationship.Manager.service;

import com.example.Customer.Relationship.Manager.model.Customer;
import com.example.Customer.Relationship.Manager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    public Customer createCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer updateCustomer(long customerId, Customer cust){
        Customer customer =  customerRepo.findById(customerId).orElseThrow(() -> new ResourceAccessException("Customer not found with Id: "+ customerId));
        customer.setFirstName(cust.getFirstName());
        customer.setLastName(cust.getLastName());
        customer.setEmail(cust.getEmail());

        return customer;
    }

    public String deleteCustomer(long customerId){
        try {
            customerRepo.deleteById(customerId);
        } catch (EmptyResultDataAccessException e) {
            return "Customer with ID " + customerId + " does not exist.";
        } catch (Exception e) {
            return "An error occurred while deleting the customer: " + e.getMessage();
        }
        return "Deleted Successfully";
    }


}
