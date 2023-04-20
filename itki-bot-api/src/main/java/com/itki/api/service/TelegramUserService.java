package com.itki.api.service;

import com.itki.api.model.TelegramUser;
import java.util.List;

public interface TelegramUserService extends CrudService<TelegramUser> {
  List<String> getAllExternalChatIds();
}
