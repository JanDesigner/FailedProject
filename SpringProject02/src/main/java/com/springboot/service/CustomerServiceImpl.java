package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.Customer;
import com.springboot.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl  implements CustomerService {
	
	@Autowired 
	CustomerRepository customerRepository;

	@Override
	public List<Customer> getListCustomer() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public void getAddCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.getCustomerByEmail(email);
	}

	@Override
	public Customer getCustomerById(Integer id) {
		Customer customer = customerRepository.findById(id).get();
		return customer;
	}

}
