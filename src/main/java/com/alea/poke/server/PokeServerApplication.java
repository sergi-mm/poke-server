package com.alea.poke.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.alea.poke")
@EnableScheduling
@EnableTransactionManagement
@EnableAutoConfiguration
@EntityScan(basePackages = "com.alea.poke.domain")
@EnableJpaRepositories(basePackages = "com.alea.poke.repository")
public class PokeServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PokeServerApplication.class, args);
	}
}
