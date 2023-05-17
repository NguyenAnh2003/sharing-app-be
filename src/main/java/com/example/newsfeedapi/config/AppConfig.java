package com.example.newsfeedapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Configuration
public class AppConfig {
    @Bean
    public ZonedDateTime getCurrentDateTime() {
        return ZonedDateTime.now(ZoneId.systemDefault());
    }
}
