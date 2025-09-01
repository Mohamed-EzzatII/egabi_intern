package com.example.demo.security;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // each req should be authorized
        http.authorizeHttpRequests(request -> request
                .requestMatchers("login", "register").permitAll() //allow login and register
                .requestMatchers("/students/list").hasRole("ADMIN")
                .anyRequest().authenticated());

        // create new session for every login
        http.sessionManagement(
                session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        // disable csrf
        http.csrf(AbstractHttpConfigurer::disable);

        // no login form
        http.httpBasic(Customizer.withDefaults());
        // no login form
        // http.formLogin(Customizer.withDefaults());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // in memory
/*
    @Bean
    public UserDetailsService userDetailsService(){
        System.out.println("userDetailsService");

        // using in memory user
        UserDetails user = User.withDefaultPasswordEncoder() // use the default generated password at first
                .username("ezzat").password("e@123").roles("Admin").build();
        return new InMemoryUserDetailsManager(user);

  }

 */
    // db authentication
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // password hashing
        daoAuthenticationProvider.setUserDetailsService(userDetailsService); // service for the user authentication
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
