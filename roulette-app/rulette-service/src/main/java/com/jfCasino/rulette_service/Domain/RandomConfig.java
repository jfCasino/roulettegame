package com.jfCasino.rulette_service.Domain;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomConfig {
    //JF Secure random skrbi za random number generator, singleton scoped
    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom(); // Seeded automatically by OS
    }

}
