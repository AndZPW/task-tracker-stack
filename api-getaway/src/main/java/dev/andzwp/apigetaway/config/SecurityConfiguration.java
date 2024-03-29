package dev.andzwp.apigetaway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity
                .authorizeExchange(
                        authorizeExchangeSpec -> authorizeExchangeSpec.pathMatchers("/task-app/tasks/**").authenticated()
                )
                .oauth2ResourceServer(
                        x -> x.jwt(Customizer.withDefaults())
                )
                .csrf(
                        ServerHttpSecurity.CsrfSpec::disable
                )
                .build();
    }
}
