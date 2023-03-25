package com.itki.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
