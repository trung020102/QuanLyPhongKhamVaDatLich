package com.quanlyphongkhamvadatlich.security;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.quanlyphongkhamvadatlich.enums.EnumRole;

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
                                .authorizeHttpRequests((authorize) -> authorize
                                                .requestMatchers("/client/css/**",
                                                                "/client/images/**",
                                                                "/client/js/**",
                                                                "/client/plugins/**",
                                                                "/client/register",
                                                                "/client/home",
                                                                "/client/about",
                                                                "/client/procedure",
                                                                "/client/faqs",
                                                                "/client/save",
                                                                "/client/verifyEmail")
                                                .permitAll()
                                                .requestMatchers("/client/**")
                                                .hasAuthority(EnumRole.CLIENT.name()))

                                .formLogin(form -> form.loginPage("/client/login").permitAll()
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/client/personalinfo", true)
                                                .loginProcessingUrl("/client/authenticate"))

                                .logout(logout -> logout
                                                .logoutRequestMatcher(
                                                                new AntPathRequestMatcher("/client/logout", "GET"))
                                                .logoutSuccessUrl("/client/login")
                                                .deleteCookies("JSESSIONID"))

                                .exceptionHandling(ex -> ex.accessDeniedPage("/errors/403"));

                return http.build();
        }
}
