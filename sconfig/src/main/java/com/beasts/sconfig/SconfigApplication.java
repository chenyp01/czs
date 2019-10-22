package com.beasts.sconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SconfigApplication.class, args);
	}
}
