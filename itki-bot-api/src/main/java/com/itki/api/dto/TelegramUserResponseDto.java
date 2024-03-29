package com.itki.api.dto;

import lombok.Data;

@Data
public class TelegramUserResponseDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String externalChatId;
}
