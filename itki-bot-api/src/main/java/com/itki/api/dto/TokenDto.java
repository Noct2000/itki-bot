package com.itki.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenDto {
  @NotBlank
  private String token;
  @NotBlank
  private String refreshToken;
}
