package com.quanlyphongkhamvadatlich.configs.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ClientSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProviderForClient() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChainForClient(HttpSecurity http) throws Exception {
        http.securityMatcher("/client/**");
        http.authenticationProvider(authenticationProviderForClient());
        http
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/client/personalinfo",
                                "/client/booking",
                                "/client/booking/appointment",
                                "/booking/appointment/success").hasAuthority("CLIENT")
                )
                .formLogin(form ->
                        form.loginPage("/client/login").permitAll()
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/client/home")
                                .loginProcessingUrl("/user/authenticate")
                );

        return http.build();
    }
}
