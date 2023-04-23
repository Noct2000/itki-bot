package com.itki.api.controller;

import com.itki.api.dto.LoginRequestDto;
import com.itki.api.dto.LoginResponseDto;
import com.itki.api.service.AuthenticationService;
import javax.validation.Valid;
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
  public ResponseEntity<LoginResponseDto> login(
      @Valid @RequestBody LoginRequestDto loginRequestDto
  ) {
    Optional<LoginResponseDto> loginResponseDto = authenticationService
        .login(loginRequestDto.getLogin(), loginRequestDto.getPassword());
    return getLoginResponseDto(loginResponseDto);
  }

  @PostMapping("/refresh")
  public ResponseEntity<LoginResponseDto> refresh(@RequestBody String refreshToken) {
    Optional<LoginResponseDto> loginResponseDto = authenticationService.login(refreshToken);
    return getLoginResponseDto(loginResponseDto);
  }

  private ResponseEntity<LoginResponseDto> getLoginResponseDto(
      Optional<LoginResponseDto> loginResponseDto
  ) {
    if (loginResponseDto.isPresent()) {
      return ResponseEntity.ok(loginResponseDto.get());
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }
}
