package com.itdragon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class ITDragonWebConfig {

    @Bean
    public LocaleResolver localeResolver() {
        return  new ITDragonLocaleResolver();
    }

}
