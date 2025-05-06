package com.aakash.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class Appconfig {

 //   @Bean
 //   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//        .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/**").authenticated().anyRequest().permitAll()) // ✅ Public endpoints
//                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class).csrf().disable().cors().configurationSource(new CorsConfigurationSource() {
//					
//					@Override
//					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//						// TODO Auto-generated method stub
//						CorsConfiguration cfg=new CorsConfiguration();
//						cfg.setAllowedOrigins(Arrays.asList("https://localhost:3000"));
//						
//						 cfg.setAllowedOrigins(List.of("http://localhost:3000")); // ✅ No wildcard (*) when using credentials
//					        //cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//						 cfg.setAllowedHeaders(Collections.singletonList("*"));
//					        cfg.setExposedHeaders(Arrays.asList("Authorization"));
//					        cfg.setAllowCredentials(true); // ✅ Allow credentials
//					        cfg.setMaxAge(3600L);
//						return cfg;
//					}
//				})
//                .and().httpBasic().and().formLogin();
//                return http.build();
        
    	//chatGpt
    @Bean
    @Order(1) // ✅ Ensures this security configuration takes priority
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/signup", "/auth/login").permitAll() // ✅ Allow signup & login
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll())
            .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
            .csrf().disable()
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(List.of("http://localhost:3000"));
                cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                cfg.setAllowedHeaders(List.of("*"));
                cfg.setExposedHeaders(List.of("Authorization"));
                cfg.setAllowCredentials(true);
                cfg.setMaxAge(3600L);
                return cfg;
            }));

        return http.build();
    }
	
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
