package com.itki.api.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
  @NotBlank
  private String login;
  @NotBlank
  private String password;
}
