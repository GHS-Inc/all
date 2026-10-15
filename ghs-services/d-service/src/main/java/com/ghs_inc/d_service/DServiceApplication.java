package com.ghs_inc.d_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DServiceApplication.class, args);
	}

}
