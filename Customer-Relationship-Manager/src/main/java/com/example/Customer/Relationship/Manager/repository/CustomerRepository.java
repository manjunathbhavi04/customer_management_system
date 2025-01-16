package com.example.Customer.Relationship.Manager.repository;

import com.example.Customer.Relationship.Manager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
