package com.example.footballclubapi.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BearerAuth", scheme = "bearer")
@RequiredArgsConstructor
//@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeReq -> authorizeReq.requestMatchers(
                                "/api/v1/auth/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "v3/api-docs/**",
                                "/api/v1/clothing/**",
                                "/api/v1/shoes/**",
                                "/api/v1/manufacturers/**",
                                "/api/v1/clothing-types/**",
                                "/api/v1/shoes-types/**",
                                "/api/v1/clothing",
                                "/api/v1/shoes",
                                "/api/v1/manufacturers",
                                "/api/v1/clothing-types",
                                "/api/v1/shoes-types",
                                "/api/v1/users/**",
                                "/api/v1/users",
                                "/api/v1/carts",
                                "/api/v1/shoe-sizes",
                                "/api/v1/shoe-sizes/**",
                                "/api/v1/cloth-sizes",
                                "/api/v1/shoe-sizes/**",
                                "/api/v1/cart-clothing",
                                "/api/v1/cart-clothing/**",
                                "/api/v1/cart-shoes",
                                "/api/v1/cart-shoes/**",
                                "/api/v1/orders",
                                "/api/v1/orders/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

