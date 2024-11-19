package com.br.fiap.customer.configuration.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        final var httpValid = "http://localhost:4200,http://localhost:8080/";
        final String[] allowedOrigins = httpValid.split(",");
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(allowedOrigins);
    }
}