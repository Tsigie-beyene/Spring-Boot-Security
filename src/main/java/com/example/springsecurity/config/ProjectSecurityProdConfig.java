package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("prod")
class ProjectSecurityProdConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http.redirectToHttps(withDefaults());
        http.csrf(csrfConfig -> csrfConfig.disable());
//        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccounts","/myBalance","/myLoans","/myCards").authenticated()
                        .requestMatchers("/notices","/contact","/error","/register").permitAll());
//        http.formLogin(flc-> flc.disable());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();

    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user= User.withUsername("user").password("{noop}UserTsigie@12345").authorities("read").build();
//        UserDetails admin= User.withUsername("admin").password("{bcrypt}$2a$12$7m1vHikebUfZIOHptrML3O/C.9LyW2QZwFwMJpIBVmQaTC2XvVi2C").authorities("admin").build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }
//     @Bean
//     public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource );
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}

