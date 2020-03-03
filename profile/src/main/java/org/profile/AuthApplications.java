package org.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaAuditing
@EntityScan("org.domain.model")
@EnableJpaRepositories("org.repository.repo")
public class AuthApplications {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplications.class, args);
	}

}
