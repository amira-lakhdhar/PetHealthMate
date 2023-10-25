package com.example.eureukaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EureukaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EureukaServerApplication.class, args);
    }

}
