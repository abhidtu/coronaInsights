package com.corona.insights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronainsightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronainsightsApplication.class, args);
	}

}
