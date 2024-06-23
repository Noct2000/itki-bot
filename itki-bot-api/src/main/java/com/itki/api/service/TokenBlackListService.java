package com.itki.api.service;

public interface TokenBlackListService {
  void putTokenToBlackList(String token);

  void putRefreshTokenToBlackList(String token);

  boolean isTokenInBlackList(String token);
}
