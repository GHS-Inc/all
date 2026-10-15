package com.ghs_inc.ghs_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class GhsConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(GhsConfigApplication.class, args);
	}

}
