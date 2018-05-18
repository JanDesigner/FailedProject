package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.Customer;
import com.springboot.model.User;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	public Customer getCustomerByEmail(String email);
}
