package com.centralisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CentralisationApplication {
	public static void main(String[] args) {
		SpringApplication.run(CentralisationApplication.class, args);
	}
}
