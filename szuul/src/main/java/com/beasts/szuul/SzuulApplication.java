package com.beasts.szuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SzuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SzuulApplication.class, args);
	}
}
