package com.mitrais.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mitrais.registration.model.Customer;
import com.mitrais.registration.service.CustomerService;

@SpringBootTest
class MitraisRegistrationApplicationTests {

	@Autowired
	private CustomerService customerService;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Customer customer = null;
	
	@BeforeEach
    public void init() {
		try {
			customer = new Customer(1L, "+6281234567890", "dimas", "harry", dateFormat.parse("1994-01-04 00:00:00"), "male", "dimas@test.com");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	void testAddCustomer() throws ParseException {
		Customer newCustomer = new Customer(null, "+6281234567891", "dimas", "harry", dateFormat.parse("1994-01-04 00:00:00"), "male", "dimash@test.com");
		assertEquals("+6281234567891", customerService.saveCustomer(newCustomer).getMobileNumber());
		assertEquals("dimash@test.com", customerService.saveCustomer(newCustomer).getEmail());
	}

	@Test
	void testFindCustomerById() {
		Customer saved = customerService.findCustomerById(1L);
		assertEquals(customer, saved);
	}
	
	@Test
	void testFindCustomerByEmail() {
		Customer saved = customerService.findCustomerByEmail("dimas@test.com");
		assertEquals(customer, saved);
	}
	
	@Test
	void testFindCustomerByMobileNumber() {
		Customer saved = customerService.findCustomerByMobileNumber("+6281234567890");
		assertEquals(customer, saved);
	}
	
}
