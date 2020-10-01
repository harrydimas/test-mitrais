package com.mitrais.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitrais.registration.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public Customer findByMobileNumber(String mobileNumber);
	
	public Customer findByEmail(String email);
	
}