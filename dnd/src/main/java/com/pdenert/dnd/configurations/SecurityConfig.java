package com.pdenert.dnd.configurations;

import com.pdenert.dnd.configurations.filters.JwtFilter;
import com.pdenert.dnd.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.formLogin(Customizer.withDefaults());                                              // enable login form
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                        request
                                .requestMatchers("users/register","users/login")
                                .permitAll();                                                       // allow access without credentials to above endpoints

                        request
                                .requestMatchers("/admin/**").hasRole("ADMIN");            // only allow admins
                        request
                                .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN");
                        request.anyRequest().authenticated();                                       // auth all requests not mentioned

                })                        // auth all requests
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // set sessions to stateless to prevent
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();     // use db to auth

        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));       // use bcrypt to verify
        authenticationProvider.setUserDetailsService(userDetailsService);                       // set to custom user details service
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
