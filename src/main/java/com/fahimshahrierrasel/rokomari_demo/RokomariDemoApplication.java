package com.fahimshahrierrasel.rokomari_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RokomariDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RokomariDemoApplication.class, args);
	}
}