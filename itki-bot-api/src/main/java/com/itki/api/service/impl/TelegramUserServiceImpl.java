package com.itki.api.service.impl;

import com.itki.api.model.TelegramUser;
import com.itki.api.repository.TelegramUserRepository;
import com.itki.api.service.TelegramUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class TelegramUserServiceImpl
    extends CrudServiceImpl<TelegramUser> implements TelegramUserService {
  private final TelegramUserRepository telegramUserRepository;

  public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
    super(telegramUserRepository, TelegramUser.class.getSimpleName());
    this.telegramUserRepository = telegramUserRepository;
  }

  @Override
  public TelegramUser save(TelegramUser entity) {
    Optional<TelegramUser> telegramUserOptional = telegramUserRepository
        .findTelegramUserByExternalChatId(entity.getExternalChatId());
    if (telegramUserOptional.isEmpty()) {
      return telegramUserRepository.save(entity);
    }
    log.info("duplicate telegramUser with externalChatId: '{}'", entity.getExternalChatId());
    return telegramUserOptional.get();
  }

  @Override
  public List<String> getAllExternalChatIds() {
    return telegramUserRepository.getAllExternalChatIds();
  }
}
