package com.itki.api.controller;

import com.itki.api.dto.LoginRequestDto;
import com.itki.api.dto.TokenDto;
import com.itki.api.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(
      @Valid @RequestBody LoginRequestDto loginRequestDto
  ) {
    Optional<TokenDto> loginResponseDto = authenticationService
        .login(loginRequestDto.getLogin(), loginRequestDto.getPassword());
    return getLoginResponseDto(loginResponseDto);
  }

  @PostMapping("/refresh")
  public ResponseEntity<TokenDto> refresh(@RequestBody String refreshToken) {
    Optional<TokenDto> loginResponseDto = authenticationService.login(refreshToken);
    return getLoginResponseDto(loginResponseDto);
  }

  @PostMapping("/logout")
  public ResponseEntity<Boolean> logout(@Valid @RequestBody TokenDto tokenDto) {
    authenticationService.logout(tokenDto.getToken(), tokenDto.getRefreshToken());
    return ResponseEntity.ok(true);
  }

  private ResponseEntity<TokenDto> getLoginResponseDto(
      Optional<TokenDto> loginResponseDto
  ) {
    if (loginResponseDto.isPresent()) {
      return ResponseEntity.ok(loginResponseDto.get());
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }
}
