package com.itki.api.service.impl;

import com.itki.api.service.TokenBlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenBlackListServiceImpl implements TokenBlackListService {
  private final RedisTemplate<String, String> tokenBlackListCache;
  @Value("#{T(java.time.Duration).ofMillis('${JWT_TOKEN_TTL}')}")
  private Duration blackListAuthTokenTtl;
  @Value("#{T(java.time.Duration).ofDays('${REFRESH_TOKEN_TTL_IN_DAYS}')}")
  private Duration blackListRefreshTokenTtl;

  @Override
  public void putTokenToBlackList(String token) {
    tokenBlackListCache.opsForValue().set(token, null, blackListAuthTokenTtl);
  }

  @Override
  public void putRefreshTokenToBlackList(String token) {
    tokenBlackListCache.opsForValue().set(token, null, blackListRefreshTokenTtl);
  }

  @Override
  public boolean isTokenInBlackList(String token) {
    return Boolean.TRUE.equals(tokenBlackListCache.hasKey(token));
  }
}
