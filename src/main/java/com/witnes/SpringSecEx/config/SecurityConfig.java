package com.witnes.SpringSecEx.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


      return  http
              //disable csrf
                .csrf(customizer -> customizer.disable())
              //allow authentication login page
                .authorizeHttpRequests(request ->request.anyRequest().authenticated())
              //enable in postman
                .httpBasic(Customizer.withDefaults())
                //make http to be stateless request
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

        //enable form login
        //http.formLogin(Customizer.withDefaults());
    }
}
