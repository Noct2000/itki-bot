package com.itki.api.jwt;

import io.jsonwebtoken.JwtException;
import java.io.IOException;
import java.util.Optional;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws IOException, ServletException {
    Optional<String> token = jwtTokenProvider.resolveTokenFromRequest(request);
    if (token.isPresent()) {
      try {
        jwtTokenProvider.validateToken(token.get());
        Authentication auth = jwtTokenProvider.getAuthentication(token.get());
        SecurityContextHolder.getContext().setAuthentication(auth);
      } catch (JwtException e) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
      }
    }
    filterChain.doFilter(request, response);
  }
}
