package com.itki.api.jwt;

import com.itki.api.service.TokenBlackListService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
  private static final int HEADER_OFFSET = 7;
  @Value("${security.jwt.token.expire-length}")
  private long accessTokenValidPeriod;
  @Value("#{T(java.time.Duration).ofDays('${REFRESH_TOKEN_TTL_IN_DAYS}')}")
  private Duration refreshTokenValidDuration;

  @Value("${security.jwt.token.secret-key}")
  private String secretKey;
  private final UserDetailsService userDetailsService;
  private final TokenBlackListService tokenBlackListService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createAccessToken(String profileName) {
    return buildToken(profileName, accessTokenValidPeriod);
  }

  public String createRefreshToken(String profileName) {
    return buildToken(profileName, refreshTokenValidDuration.toMillis());
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(
      userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public Optional<String> resolveTokenFromRequest(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    return resolveToken(bearerToken);
  }

  public Optional<String> resolveToken(String token) {
    if (token != null && token.startsWith("Bearer ")) {
      return Optional.of(token.substring(HEADER_OFFSET));
    }
    return Optional.empty();
  }

  public void validateToken(String token) {
    if (Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
      .getBody().getExpiration().before(new Date())) {
      throw new JwtException("Jwt token is expired");
    } else if (tokenBlackListService.isTokenInBlackList(token)) {
      throw new JwtException("Jwt token was used");
    }
  }

  private String buildToken(String profileName, long refreshTokenValidDuration) {
    Claims claims = Jwts.claims().setSubject(profileName);
    Date now = new Date();
    Date validity = new Date(now.getTime() + refreshTokenValidDuration);
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }
}
