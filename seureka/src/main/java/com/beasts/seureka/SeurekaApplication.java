package com.beasts.seureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SeurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeurekaApplication.class, args);
	}
}
