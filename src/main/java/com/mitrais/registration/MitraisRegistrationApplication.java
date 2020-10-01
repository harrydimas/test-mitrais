package com.mitrais.registration;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MitraisRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MitraisRegistrationApplication.class, args);
	}

	@PostConstruct
    public void init(){
      TimeZone.setDefault(TimeZone.getTimeZone("GMT+7"));
    }
}
