package com.example.adoption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AdoptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptionApplication.class, args);
	}

}
