package com.itki.api.dto;

import lombok.Data;

@Data
public class TelegramUserRequestDto {
  private String firstName;
  private String lastName;
  private String username;
  private String externalChatId;
}
