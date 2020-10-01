package com.mitrais.registration.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.registration.dto.ValidatorDto;
import com.mitrais.registration.model.Customer;
import com.mitrais.registration.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/register-customer")
    public ResponseEntity<?> register(@Valid @RequestBody Customer customer, Errors errors) {

		ValidatorDto dto = validate(customer, errors);
		errors = dto.getErrors();
		
        if (errors.hasErrors()) {
            List<String> errorList = errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errorList);
        } else {
			customer.setBirthDate(dto.getBirthDate());
			customerService.saveCustomer(customer);
		}

        return ResponseEntity.ok("success");

    }
	
	private ValidatorDto validate(Customer customer, Errors errors) {
		
		if (!customer.getMobileNumber().equals("")) {
			Pattern pattern = Pattern.compile("^\\+628[0-9]{8,11}$");
			Matcher matcher = pattern.matcher(customer.getMobileNumber());
			if (matcher.matches()) {
				Customer mobileNumberExists = customerService.findCustomerByMobileNumber(customer.getMobileNumber());
				if (mobileNumberExists != null) errors.rejectValue("mobileNumber", "error.mobileNumber", "Mobile Number already registered.");
			} else {
				errors.rejectValue("mobileNumber", "error.mobileNumber", "Only Indonesian Phone number allowed, ex: +6280011111");
			}
		}
		
		if (!customer.getEmail().equals("")) {
			Customer emailExists = customerService.findCustomerByEmail(customer.getEmail());
			if (emailExists != null) errors.rejectValue("email", "error.email", "Email already registered.");
		}
		
		Date birthDate = null;
		if (!customer.getYear().equals("") && !customer.getMonth().equals("") && !customer.getDate().equals("")) {
			try {
				String birth = customer.getYear().concat("-").concat(customer.getMonth()).concat("-").concat(customer.getDate());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MMMM-d");
				birthDate = format.parse(birth);
				if (!birth.equals(format.format(birthDate))) throw new ParseException("Birth Date invalid.", 0);
			} catch (ParseException e) {
				errors.rejectValue("birthDate", "error.birthDate", "Birth Date invalid.");
			}
		}
		
		return new ValidatorDto(errors, birthDate);
	}
	
}
