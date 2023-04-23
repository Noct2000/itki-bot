package com.itki.api.service;

import com.itki.api.dto.LoginResponseDto;
import java.util.Optional;

public interface AuthenticationService {
  Optional<LoginResponseDto> login(String login, String password);

  Optional<LoginResponseDto> login(String refreshToken);
}
