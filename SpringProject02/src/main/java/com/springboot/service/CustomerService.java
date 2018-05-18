package com.springboot.service;

import java.util.List;

import com.springboot.model.Customer;

public interface CustomerService {
	
	List<Customer> getListCustomer();
	Customer getCustomerByEmail(String email);
	void getAddCustomer(Customer customer);
	Customer getCustomerById(Integer id);
}
