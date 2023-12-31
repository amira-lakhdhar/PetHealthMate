package com.example.apigatway.Config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatwayConfig {


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("userms", r -> r.path("/pethealth/userms/**").uri("lb://userms"))
                .route("adoption-service", r -> r.path("/pethealth/adoption-service/**").uri("lb://adoption-service"))
                .route("amiraweb", r -> r.path("/pethealth/amiraweb/**").uri("lb://amiraweb"))
                .route("discovery-server", r -> r.path("/eureka/web").filters(f -> f.setPath("/")).uri("http://localhost:8761"))
                .route("discovery-server-static", r -> r.path("/eureka/**") .uri("http://localhost:8761"))
                .build();
    }
}