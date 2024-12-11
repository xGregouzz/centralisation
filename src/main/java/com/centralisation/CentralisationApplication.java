package com.centralisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CentralisationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralisationApplication.class, args);
	}
}
