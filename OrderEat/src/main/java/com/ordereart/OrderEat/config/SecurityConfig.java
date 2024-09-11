package com.ordereart.OrderEat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.signerKey}")
    String Key;

    private final String[] PUBLIC_ENDPOINTS = {
            "/user", "/users/{userId}",
            "/menu", "/menu/{menuId}",
            "totals", "/totals/{totalId}",
            "/authenticate/token", "/authenticate/introspect"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS).permitAll()
                .requestMatchers(HttpMethod.DELETE, PUBLIC_ENDPOINTS).permitAll()
                .requestMatchers(HttpMethod.PUT, PUBLIC_ENDPOINTS).permitAll()
                .anyRequest()
                .authenticated());

        security.oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(jwtDecoder()
                        )));

        security.csrf(AbstractHttpConfigurer::disable);

        return security.build();
    }

    @Bean
    JwtDecoder jwtDecoder(){

        SecretKeySpec secretKeySpec = new SecretKeySpec(Key.getBytes(),"HS512");

        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
    }
}
