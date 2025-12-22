package com.dojo.customers.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dojo.customers.entities.Customer;
import com.dojo.customers.services.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);
	private CustomerService service;

	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@RequestMapping(method =  RequestMethod.HEAD,path = "/{id}")
	public ResponseEntity<Void> existsById(@PathVariable("id") Long id) {
		if(service.exists(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<List<Customer>> findAll() {
		logger.info("Clientes : "+service.findAll().toString());
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("search")
	public ResponseEntity<List<Customer>> findByLikeUsername(@RequestParam String username) {
		return ResponseEntity.ok(service.findByLikeUsername(username));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<Customer> optionalCustomer =service.findById(id);
		if(optionalCustomer.isPresent()) {
			logger.info("Cliente : "+optionalCustomer.get());

			return ResponseEntity.ok(optionalCustomer.get());
		}
			logger.warn("Cliente con id: "+id+" no encontrado!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Collections.singletonMap("Cliente","No encontrado"));
	}

	@GetMapping("/by-user/{username}")
	public ResponseEntity<?> findByUsername(@PathVariable String username) {
		Optional<Customer> optional=service.findByUsername(username);
		if(optional.isEmpty()) {
			return new ResponseEntity<>(Collections.singletonMap("Cliente","No encontrado"), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(optional.get());
	}

	@PostMapping
	public ResponseEntity<Customer> save(@RequestBody Customer customer) {
		return ResponseEntity.ok(service.save(customer));
	}

}
