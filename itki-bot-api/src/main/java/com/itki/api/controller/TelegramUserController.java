package com.itki.api.controller;

import com.itki.api.dto.TelegramUserRequestDto;
import com.itki.api.dto.TelegramUserResponseDto;
import com.itki.api.mapper.TelegramUserDtoMapper;
import com.itki.api.model.TelegramUser;
import com.itki.api.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/telegram-users")
@RequiredArgsConstructor
public class TelegramUserController {
  private final TelegramUserService telegramUserService;
  private final TelegramUserDtoMapper telegramUserDtoMapper;

  @PostMapping
  public TelegramUserResponseDto save(@RequestBody TelegramUserRequestDto telegramUserRequestDto) {
    TelegramUser telegramUser = telegramUserDtoMapper.toModel(telegramUserRequestDto);
    telegramUser = telegramUserService.save(telegramUser);
    return telegramUserDtoMapper.toResponseDto(telegramUser);
  }

  @GetMapping
  public List<TelegramUserResponseDto> getAll() {
    return telegramUserService.findAll()
        .stream()
        .map(telegramUserDtoMapper::toResponseDto)
        .collect(Collectors.toList());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
    telegramUserService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
