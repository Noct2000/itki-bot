package com.itki.api.mapper;

import com.itki.api.dto.TelegramUserRequestDto;
import com.itki.api.dto.TelegramUserResponseDto;
import com.itki.api.model.TelegramUser;
import org.springframework.stereotype.Component;

@Component
public class TelegramUserDtoMapper {
  public TelegramUser toModel(TelegramUserRequestDto telegramUserRequestDto) {
    TelegramUser telegramUser = new TelegramUser();
    telegramUser.setFirstName(telegramUserRequestDto.getFirstName());
    telegramUser.setLastName(telegramUserRequestDto.getLastName());
    telegramUser.setUsername(telegramUserRequestDto.getUsername());
    telegramUser.setExternalChatId(telegramUserRequestDto.getExternalChatId());
    return telegramUser;
  }

  public TelegramUserResponseDto toResponseDto(TelegramUser telegramUser) {
    TelegramUserResponseDto telegramUserResponseDto = new TelegramUserResponseDto();
    telegramUserResponseDto.setId(telegramUser.getId());
    telegramUserResponseDto.setFirstName(telegramUser.getFirstName());
    telegramUserResponseDto.setLastName(telegramUser.getLastName());
    telegramUserResponseDto.setUsername(telegramUser.getUsername());
    telegramUserResponseDto.setExternalChatId(telegramUser.getExternalChatId());
    return telegramUserResponseDto;
  }
}
