package com.quanlyphongkhamvadatlich.configs.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class StaticResourcesConfig {
    @Bean
    public WebSecurityCustomizer ignoreForResources() {
        return (web) -> web.ignoring().requestMatchers("/static/**", "/client/**",  "/dashboard/**", "user_assets");
    }
}
