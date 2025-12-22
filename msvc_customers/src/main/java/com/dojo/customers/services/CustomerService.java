package com.dojo.customers.services;

import java.util.List;
import java.util.Optional;

import com.dojo.customers.entities.Customer;

public interface CustomerService {
	boolean exists(Long id);
	List<Customer> findAll();
	List<Customer> findByLikeUsername(String username);
	Optional<Customer> findById(Long id);
	Optional<Customer> findByUsername(String username);
	Customer save(Customer customer);
}
