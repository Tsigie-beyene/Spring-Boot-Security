package com.example.springsecurity.config;

import org.springframework.boot.security.autoconfigure.web.servlet.SecurityFilterProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccounts","/myBalance","/myLoans","/myCards").authenticated()
                        .requestMatchers("/notices","/contact","/error").permitAll());
//        http.formLogin(flc-> flc.disable());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();

    }
    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails user= User.withUsername("user").password("{noop}12345").authorities("read").build();
        UserDetails user= User.withUsername("user").password("{noop}12345").authorities("read").build();
        UserDetails admin= User.withUsername("admin").password("{bcrypt}$2a$12$gamqpWF8lnCrtVUiEgXSoOUMOuUGMJIKm6loRSIR24CO.7BkieC4i").authorities("admin").build();

        return new InMemoryUserDetailsManager(user,admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

