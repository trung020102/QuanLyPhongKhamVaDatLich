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
public class AdminSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProviderForAdmin() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChainForAdmin(HttpSecurity http) throws Exception {
        http.securityMatcher("/admin/**");
        http.authenticationProvider(authenticationProviderForAdmin());
        http
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                )
                .formLogin(form ->
                        form.loginPage("/admin/login").permitAll()
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/admin/dashboard/index")
                                .loginProcessingUrl("/user/authenticate")
                );

        return http.build();
    }
}
