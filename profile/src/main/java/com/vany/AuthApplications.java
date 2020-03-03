package com.vany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaAuditing
@EntityScan("org.domain.model")
public class AuthApplications {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplications.class, args);
	}

}
