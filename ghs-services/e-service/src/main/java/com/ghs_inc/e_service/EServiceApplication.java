package com.ghs_inc.e_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EServiceApplication.class, args);
	}

}
