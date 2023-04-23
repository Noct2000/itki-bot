package com.itki.api.service.impl;

import com.itki.api.service.TelegramBroadcastService;
import com.itki.api.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramBroadcastServiceImpl
    extends TelegramWebhookBot implements TelegramBroadcastService {
  private final TelegramUserService telegramUserService;
  @Value("${TELEGRAM_TOKEN}")
  private String telegramToken;
  @Value("${TELEGRAM_USERNAME}")
  private String telegramBotUsername;

  @Override
  @SneakyThrows
  public void sendFile(String caption, MultipartFile file) {
    List<String> externalChatIds = telegramUserService.getAllExternalChatIds();
    SendDocument sendDocument = new SendDocument();
    for (String externalChatId: externalChatIds) {
      sendDocument.setChatId(externalChatId);
      sendDocument.setDocument(new InputFile(file.getInputStream(), file.getOriginalFilename()));
      sendDocument.setCaption(caption);
      execute(sendDocument);
    }
  }

  @Override
  @SneakyThrows
  public void sendPhoto(String caption, MultipartFile photo) {
    SendPhoto sendPhoto = new SendPhoto();
    List<String> externalChatIds = telegramUserService.getAllExternalChatIds();
    for (String externalChatId: externalChatIds) {
      sendPhoto.setPhoto(new InputFile(photo.getInputStream(), photo.getOriginalFilename()));
      sendPhoto.setCaption(caption);
      sendPhoto.setChatId(externalChatId);
      execute(sendPhoto);
    }
  }

  @Override
  @SneakyThrows
  public void sendTextMessage(String text) {
    SendMessage sendMessage = new SendMessage();
    List<String> externalChatIds = telegramUserService.getAllExternalChatIds();
    for (String externalChatId: externalChatIds) {
      sendMessage.setChatId(externalChatId);
      sendMessage.setText(text);
      execute(sendMessage);
    }
  }

  @Override
  public String getBotUsername() {
    return telegramBotUsername;
  }

  @Override
  public String getBotToken() {
    return telegramToken;
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    return null;
  }

  @Override
  public String getBotPath() {
    return null;
  }
}
