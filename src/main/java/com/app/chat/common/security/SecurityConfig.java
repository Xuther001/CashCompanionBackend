package com.app.chat.common.security;

import com.app.chat.common.security.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    disable spring security for testing purposes
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().anyRequest().permitAll();
//        return http.build();
//    }

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and() // Have to have this or "has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource."
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/v3/api-docs/swagger-config","/v2/api-docs/**","/swagger.json","/swagger-ui.html","/swagger-resources/**","/webjars/**","/api/v1/auth/**","/api/v1/auth/authenticate","/swagger-ui/**", "/bus/v3/api-docs/**", "/ws", "/api/v1/chat/messages/sentByUser","api/v1/chat/hello", "/api/v1/notes/**","/api/v1/notes")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:3000"); // You can set the allowed origin(s) here.
//        configuration.addAllowedMethod("GET, POST, PUT, DELETE"); // You can set specific HTTP methods (e.g., GET, POST) or use "*".
//        configuration.addAllowedHeader("Content-Type"); // You can set specific HTTP headers or use "*".
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

}
