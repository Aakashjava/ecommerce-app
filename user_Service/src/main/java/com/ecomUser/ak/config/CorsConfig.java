package com.ecomUser.ak.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

	 @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration corsConfig = new CorsConfiguration();

	        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // ✅ Allow frontend origin
	        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // ✅ Allow HTTP methods
	        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // ✅ Allow necessary headers
	        corsConfig.setAllowCredentials(true); // ✅ Allow credentials (cookies, JWT)

	        source.registerCorsConfiguration("/**", corsConfig);
	        return new CorsFilter(source);
	    }
}




