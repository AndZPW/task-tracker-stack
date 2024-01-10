package dev.andzwp.apigetaway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("task-service",
                        r -> r.path("/task-app/tasks/**")
                                .filters(f -> f.rewritePath(
                                                "/task-app/tasks/(?<segment>.*)",
                                                "/api/v1/tasks/${segment}"
                                        )
                                )
                                .uri("lb://task-service")
                )
//                .route("auth-service",
//                        r -> r.path("/task-app/user/token")
//                                .filters(f -> f.redirect(200,"http://localhost:7080/realms/master/protocol/openid-connect/token"))
//                                .uri("http://localhost:7080/")
//                )
                .build();
    }
}
