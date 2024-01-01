package com.niit.todo.apiGatewayService.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/v1/**")
//                        .uri("http://localhost:8087/"))
                        .uri("lb://user-authentication-service"))
                .route(p -> p.path("/api/v2/**")
//                        .uri("http://localhost:8082"))
                        .uri("lb://user-todo-service"))
                .route(p -> p.path("/mail/**")
//                        .uri("http://localhost:8080"))
                        .uri("lb://mail-service"))
                .build();
    }

}
