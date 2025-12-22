package com.dojo.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dojo.customers.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByUsername(String username);

    @Query("select c from Customer c where c.username LIKE %:nom%")
    List<Customer> findByLikeUsername(@Param("nom") String name);

}
