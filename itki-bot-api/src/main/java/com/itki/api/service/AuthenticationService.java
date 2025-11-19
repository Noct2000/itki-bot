package com.itki.api.service;

import com.itki.api.dto.TokenDto;
import java.util.Optional;

public interface AuthenticationService {
  Optional<TokenDto> login(String login, String password);

  Optional<TokenDto> login(String refreshToken);

  void logout(String token, String refreshToken);
}
