package com.mitrais.registration.dto;

import java.util.Date;

import org.springframework.validation.Errors;

public class ValidatorDto {

	private Errors errors;
	private Date birthDate;

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public ValidatorDto(Errors errors, Date birthDate) {
		super();
		this.errors = errors;
		this.birthDate = birthDate;
	}

}
