package com.ordereart.OrderEat.config;

import com.ordereart.OrderEat.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.signerKey}")
    String Key;

    private final String[] PUBLIC_ENDPOINTS = {
            "/menu", "/menu/{menuId}",
            "/totals/{totalId}",
    };
    private final String[] TOKEN = {
            "/authenticate/token",
            "/authenticate/introspect",
            "/users/{usersId}/menu/{menuId}"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security.authorizeHttpRequests(request -> request
                //User - Menu - Total

                //POST
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()

                //GET
                .requestMatchers(HttpMethod.GET, "/users/myInfo").permitAll()
                .requestMatchers(HttpMethod.GET, "/users", "/totals", "/users/{userId}").hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS).permitAll()

                //PUT - DELETE
                .requestMatchers(HttpMethod.PUT, "/users/myInfo").permitAll()
                .requestMatchers(HttpMethod.PUT, PUBLIC_ENDPOINTS).permitAll()
                .requestMatchers(HttpMethod.DELETE, PUBLIC_ENDPOINTS).permitAll()

                //Token
                .requestMatchers(HttpMethod.POST, TOKEN).permitAll()
                .anyRequest()
                .authenticated());


        security.oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(jwtDecoder())
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())));

        security.csrf(AbstractHttpConfigurer::disable);

        return security.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(Key.getBytes(),"HS512");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
    }

    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder(10);
    }
}
