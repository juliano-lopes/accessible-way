package com.julopes.accessibleway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = { "com.julopes.accessibleway.domain" })
@ComponentScan(basePackages = { "com.julopes.accessibleway.*" })
@EnableJpaRepositories(basePackages = { "com.julopes.accessibleway.repository" })
@EnableTransactionManagement

public class AccessibleWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessibleWayApplication.class, args);
		/*
		 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); String result =
		 * encoder.encode("123"); System.out.println(result);
		 */
	}

}
