package com.finance.loan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// TODO: add pagination support

@Configuration
public class LoanRestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
