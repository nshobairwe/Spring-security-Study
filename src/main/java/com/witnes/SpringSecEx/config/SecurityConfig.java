package com.witnes.SpringSecEx.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return  http
                .csrf(csrf -> csrf.disable()) // Consider enabling in production
                .authorizeHttpRequests(request -> request
                        //.requestMatchers("/register").permitAll() // Allow access to registration
                        .anyRequest().authenticated()) // Secure all other endpoints
                .httpBasic(Customizer.withDefaults()) // Enable basic authentication for Postman
                .formLogin(Customizer.withDefaults()) // Enable form login
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // Replace with BCrypt in production
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    // Uncomment to define custom in-memory users
    // @Bean
    // public UserDetailsService userDetailService() {
    //     UserDetails user1 = User
    //             .withDefaultPasswordEncoder()
    //             .username("wit")
    //             .password("123")
    //             .roles("USER")
    //             .build();
    //     UserDetails user2 = User
    //             .withDefaultPasswordEncoder()
    //             .username("witnes")
    //             .password("123")
    //             .roles("USER")
    //             .build();
    //     return new InMemoryUserDetailsManager(user1, user2);
    // }
}
