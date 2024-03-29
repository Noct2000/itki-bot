package com.itki.api.config;

import com.google.common.collect.ImmutableList;
import com.itki.api.jwt.JwtConfigurer;
import com.itki.api.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.headers().frameOptions().sameOrigin()
      .and()
      .csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().authorizeRequests()
      .antMatchers(
          HttpMethod.GET,
          "/questions",
          "/curators",
          "/curators/**",
          "/groups",
          "/telegram-users"
          ).hasAnyRole("ADMIN", "USER")
      .antMatchers(
          HttpMethod.POST,
          "/questions",
          "/curators",
          "/groups",
          "/telegram-users",
          "/tg/**"
      ).hasRole("ADMIN")
      .antMatchers(
          HttpMethod.DELETE,
          "/questions/**",
          "/curators/**",
          "/groups/**",
          "/telegram-users/**"
      ).hasRole("ADMIN")
      .antMatchers("/success").permitAll()
      .antMatchers("/login", "/refresh").permitAll()
      .anyRequest().authenticated()
      .and()
      .apply(new JwtConfigurer(jwtTokenProvider))
      .and().cors();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(ImmutableList.of("*"));
    configuration.setAllowedMethods(ImmutableList.of("*"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(ImmutableList.of("*"));
    configuration.setExposedHeaders(ImmutableList.of("Access-Control-Allow-Origin",
        "Access-Control-Allow-Methods", "Access-Control-Allow-Headers", "Access-Control-Max-Age",
        "Access-Control-Request-Headers", "Access-Control-Request-Method"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
