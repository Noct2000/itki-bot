package com.itki.api.service.impl;

import com.itki.api.dto.TokenDto;
import com.itki.api.jwt.JwtTokenProvider;
import com.itki.api.model.User;
import com.itki.api.service.AuthenticationService;
import com.itki.api.service.TokenBlackListService;
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
  private final TokenBlackListService tokenBlackListService;

  @Override
  public Optional<TokenDto> login(String login, String password) {
    Optional<User> user = userService.findByLogin(login);
    if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
      return Optional.empty();
    }
    return Optional.of(getLoginResponse(user.get()));
  }

  @Override
  public Optional<TokenDto> login(String refreshToken) {
    jwtTokenProvider.validateToken(refreshToken);
    Optional<User> user = userService
        .findByLogin(jwtTokenProvider.getUsername(refreshToken));
    return user.map(this::getLoginResponse);
  }

  @Override
  public void logout(String token, String refreshToken) {
    jwtTokenProvider.validateToken(token);
    jwtTokenProvider.validateToken(refreshToken);
    tokenBlackListService.putTokenToBlackList(token);
    tokenBlackListService.putRefreshTokenToBlackList(refreshToken);
  }

  private TokenDto getLoginResponse(User user) {
    String accessToken = jwtTokenProvider.createAccessToken(user.getLogin());
    String refreshToken = jwtTokenProvider.createRefreshToken(user.getLogin());
    TokenDto tokenDto = new TokenDto();
    tokenDto.setToken(accessToken);
    tokenDto.setRefreshToken(refreshToken);
    return tokenDto;
  }


}
