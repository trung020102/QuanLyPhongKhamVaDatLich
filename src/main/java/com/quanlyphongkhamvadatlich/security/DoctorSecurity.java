package com.quanlyphongkhamvadatlich.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.quanlyphongkhamvadatlich.enums.EnumRole;

@Configuration
@EnableWebSecurity
public class DoctorSecurity {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProviderForDoctor() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChainForDoctor(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/doctor/**")
                .authenticationProvider(authenticationProviderForDoctor())
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/doctor/**").hasAuthority(EnumRole.DOCTOR.name())
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/errors/403"));
        ;

        return http.build();
    }
}
