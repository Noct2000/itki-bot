package com.itki.api.config;

import com.itki.api.jwt.JwtConfigurer;
import com.itki.api.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

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
          "/telegram-users",
          "/telegram-users/**"
          ).hasAnyRole("ADMIN", "USER")
      .antMatchers(
          HttpMethod.POST,
          "/questions",
          "/curators",
          "/groups",
          "/telegram-users"
      ).hasRole("ADMIN")
      .antMatchers(
          HttpMethod.DELETE,
          "/questions/**",
          "/curators/**",
          "/groups/**",
          "/telegram-users/**"
      ).hasRole("ADMIN")
      .antMatchers("/success").permitAll()
      .antMatchers("/login").permitAll()
      .anyRequest().authenticated()
      .and()
      .apply(new JwtConfigurer(jwtTokenProvider))
      .and().cors();
  }
}
