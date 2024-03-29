package com.itki.api.service.impl;

import com.itki.api.dto.LoginResponseDto;
import com.itki.api.jwt.JwtTokenProvider;
import com.itki.api.model.User;
import com.itki.api.service.AuthenticationService;
import com.itki.api.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public Optional<LoginResponseDto> login(String login, String password) {
    Optional<User> user = userService.findByLogin(login);
    if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
      return Optional.empty();
    }
    return Optional.of(getLoginResponse(user.get()));
  }

  @Override
  public Optional<LoginResponseDto> login(String refreshToken) {
    Optional<User> user = userService
        .findByLogin(jwtTokenProvider.getUsername(refreshToken));
    return user.map(this::getLoginResponse);
  }

  private LoginResponseDto getLoginResponse(User user) {
    String accessToken = jwtTokenProvider.createAccessToken(user.getLogin());
    String refreshToken = jwtTokenProvider.createRefreshToken(user.getLogin());
    LoginResponseDto loginResponseDto = new LoginResponseDto();
    loginResponseDto.setToken(accessToken);
    loginResponseDto.setRefreshToken(refreshToken);
    return loginResponseDto;
  }


}
