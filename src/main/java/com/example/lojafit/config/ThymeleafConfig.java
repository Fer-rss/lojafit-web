package com.example.lojafit.config;

import com.example.lojafit.util.FormatadorUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
public class ThymeleafConfig {

    @Bean
    public FormatadorUtil formatadorUtil() {
        return new FormatadorUtil();
    }
}