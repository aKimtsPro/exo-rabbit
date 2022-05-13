package bstorm.akimts.gateway2.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// les deux?
//@Configuration
public class RouteLocatorConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("facturation-service", r -> r.path("/factures/**")
                        .filters(f -> f.rewritePath("/factures/(?<path>.*)", "/$\\{path}"))
                        .uri("lb://facturation-service"))
                .build();
    }
}
