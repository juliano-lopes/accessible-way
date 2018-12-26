package com.julopes.accessibleway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = { "domain" })
@ComponentScan(basePackages = { "controller" })
@EnableJpaRepositories(basePackages = { "repository" })
@EnableTransactionManagement

public class AccessibleWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessibleWayApplication.class, args);
	}

}
