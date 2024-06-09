package com.itki.api.config;

import com.itki.api.jwt.JwtTokenFilter;
import com.itki.api.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  private final JwtTokenProvider jwtTokenProvider;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
    http.authorizeHttpRequests(authManager -> authManager
        .requestMatchers(
            HttpMethod.GET,
            "/questions",
            "/curators",
            "/curators/**",
            "/groups",
            "/telegram-users"
        ).hasAnyRole("ADMIN", "USER")
        .requestMatchers(
            HttpMethod.POST,
            "/questions",
            "/curators",
            "/groups",
            "/telegram-users",
            "/tg/**"
        ).hasRole("ADMIN")
        .requestMatchers(
            HttpMethod.DELETE,
            "/questions/**",
            "/curators/**",
            "/groups/**",
            "/telegram-users/**"
        ).hasRole("ADMIN")
        .requestMatchers("/success").permitAll()
        .requestMatchers("/login", "/refresh").permitAll()
        .anyRequest().authenticated()
    );
    http.csrf(AbstractHttpConfigurer::disable).sessionManagement(
        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    );
    http.addFilterBefore(
        new JwtTokenFilter(jwtTokenProvider),
        UsernamePasswordAuthenticationFilter.class
    );
    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(List.of("*"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setExposedHeaders(List.of("Access-Control-Allow-Origin",
        "Access-Control-Allow-Methods", "Access-Control-Allow-Headers", "Access-Control-Max-Age",
        "Access-Control-Request-Headers", "Access-Control-Request-Method"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
