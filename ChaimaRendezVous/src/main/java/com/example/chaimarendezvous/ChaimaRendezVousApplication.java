package com.example.chaimarendezvous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ChaimaRendezVousApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaimaRendezVousApplication.class, args);
    }

}
