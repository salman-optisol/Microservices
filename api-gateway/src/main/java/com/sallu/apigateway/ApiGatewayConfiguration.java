package com.sallu.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/exchange-converter/**")
                        .uri("lb://currency-conversion-service"))
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange-service"))
                .build();
    }
}
