package com.example.loan.servicing.demo.config;

import com.example.loan.servicing.demo.service.InterestRateService;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public InterestRateService interestRateService() {
        return new InterestRateService();
    }
}
