package com.fdmgroup.getaways.configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.fdmgroup.getaways.service", "com.fdmgroup.getaways.model",  "com.fdmgroup.getaways.repository"})
public class Config {
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		return Persistence.createEntityManagerFactory("Getaways");
	}
	
	@Bean
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory ) {
		return entityManagerFactory.createEntityManager();
	}
}
