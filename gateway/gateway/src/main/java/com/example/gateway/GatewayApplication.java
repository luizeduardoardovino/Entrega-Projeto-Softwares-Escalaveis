package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route("at-service", r -> r
	            .path("/at/**") 
	            .filters(f -> f.stripPrefix(1))  
	            .uri("http://at-service:8080"))  

	        .route("notificacao-service", r -> r
	            .path("/notificacao/**")  
	            .filters(f -> f.stripPrefix(1))  
	            .uri("http://notificacao-service:9000")) 

	        .build();
	}


}
