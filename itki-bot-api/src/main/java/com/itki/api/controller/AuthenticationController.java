package com.itki.api.controller;

import com.itki.api.dto.LoginRequestDto;
import com.itki.api.dto.LoginResponseDto;
import com.itki.api.service.AuthenticationService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    return authenticationService.login(loginRequestDto.getLogin(), loginRequestDto.getPassword());
  }
}
