package com.itki.api.service;

import com.itki.api.dto.LoginResponseDto;

public interface AuthenticationService {
  LoginResponseDto login(String login, String password);
}
