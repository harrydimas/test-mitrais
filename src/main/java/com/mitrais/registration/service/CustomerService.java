package com.mitrais.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.registration.model.Customer;
import com.mitrais.registration.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	public Customer findCustomerById(Long id) {
		return customerRepository.findById(id).orElse(null);
	}
	
	public Customer findCustomerByMobileNumber(String mobileNumber) {
		return customerRepository.findByMobileNumber(mobileNumber);
	}
	
	public Customer findCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
}
