package com.quanlyphongkhamvadatlich.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter {
        @Bean
        public GrantedAuthoritiesMapper  grantedAuthoritiesMapper() {
            // Use SimpleAuthorityMapper to remove the ROLE_ prefix
            SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
            authorityMapper.setConvertToUpperCase(false); // To maintain case sensitivity
            return authorityMapper;
        }

        @Bean
        public UserDetailsService userDetailsServiceApp1() {
            UserDetails user = User.withUsername("admin")
                    .password(encoder().encode("admin"))
                    .roles("ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(user);
        }

        @Bean
        public static PasswordEncoder encoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChainApp1(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
            MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
            http.securityMatcher("/client*")
                    .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                            authorizationManagerRequestMatcherRegistry.requestMatchers(mvcMatcherBuilder.pattern("/client*")).hasRole("CUSTOMER"))
                    // log in
                    .formLogin(httpSecurityFormLoginConfigurer ->
                            httpSecurityFormLoginConfigurer.loginPage("/client/login")
                                    .loginProcessingUrl("client/pages/login")
                                    .failureUrl("/client/login?error=loginError")
                                    .defaultSuccessUrl("/client/home"))
                    // logout
                    .logout(httpSecurityLogoutConfigurer ->
                            httpSecurityLogoutConfigurer.logoutUrl("/client/logout")
                                    .logoutSuccessUrl("/client/login")
                                    .deleteCookies("JSESSIONID"))
                    .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                            httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403"))
                    .csrf(AbstractHttpConfigurer::disable);

            return http.build();
        }
    }
}
