package com.itki.api.service.impl;

import com.itki.api.service.TelegramBroadcastService;
import com.itki.api.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TelegramBroadcastServiceImpl
    extends TelegramWebhookBot implements TelegramBroadcastService {
  private static final String BLOCKING_MESSAGE = """
      Error sending message: \
      [403] Forbidden: bot was blocked by the user\
      """;
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
      sendFileToChat(
          caption,
          this.toInputFile(file.getInputStream(), file.getOriginalFilename()),
          externalChatId,
          sendDocument
      );
    }
  }

  @Override
  @SneakyThrows
  public void sendFile(String caption, InputStream file, String filename) {
    List<String> externalChatIds = telegramUserService.getAllExternalChatIds();
    SendDocument sendDocument = new SendDocument();
    for (String externalChatId: externalChatIds) {
      sendFileToChat(
          caption,
          this.toInputFile(file, filename),
          externalChatId,
          sendDocument
      );
    }
  }

  @Override
  @SneakyThrows
  public void sendPhoto(String caption, MultipartFile photo) {
    SendPhoto sendPhoto = new SendPhoto();
    List<String> externalChatIds = telegramUserService.getAllExternalChatIds();
    for (String externalChatId: externalChatIds) {
      sendPhotoToSpecialChat(
          caption,
          this.toInputFile(photo.getInputStream(), photo.getOriginalFilename()),
          externalChatId,
          sendPhoto
      );
    }
  }

  @Override
  @SneakyThrows
  public void sendPhoto(String caption, InputStream photo, String filename) {
    SendPhoto sendPhoto = new SendPhoto();
    List<String> externalChatIds = telegramUserService.getAllExternalChatIds();
    for (String externalChatId: externalChatIds) {
      sendPhotoToSpecialChat(caption, this.toInputFile(photo, filename), externalChatId, sendPhoto);
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
      try {
        execute(sendMessage);
      } catch (TelegramApiRequestException telegramApiRequestException) {
        handleUserBlocking(externalChatId, telegramApiRequestException);
      }
    }
  }

  @Override
  @SneakyThrows
  public void sendPhotoMediaGroup(MultipartFile[] photos) {
    SendMediaGroup sendMediaGroup = new SendMediaGroup();
    List<String> externalChatIds = telegramUserService.getAllExternalChatIds();
    for (String externalChatId: externalChatIds) {
      sendMediaGroup.setMedias(
          Arrays.stream(photos).map(this::toInputPhotoMedia).collect(Collectors.toList())
      );
      sendMediaGroup.setChatId(externalChatId);
      try {
        execute(sendMediaGroup);
      } catch (TelegramApiRequestException telegramApiRequestException) {
        handleUserBlocking(externalChatId, telegramApiRequestException);
      }
    }
  }

  @Override
  @SneakyThrows
  public void sendPhotoMediaGroup(Map<String, InputStream> filenameInputStreamMap) {
    SendMediaGroup sendMediaGroup = new SendMediaGroup();
    List<String> externalChatIds = telegramUserService.getAllExternalChatIds();
    for (String externalChatId: externalChatIds) {
      sendMediaGroup.setMedias(
          filenameInputStreamMap.entrySet().stream()
              .map(entry -> this.toInputPhotoMedia(entry.getValue(), entry.getKey()))
              .collect(Collectors.toList())
      );
      sendMediaGroup.setChatId(externalChatId);
      try {
        execute(sendMediaGroup);
      } catch (TelegramApiRequestException telegramApiRequestException) {
        handleUserBlocking(externalChatId, telegramApiRequestException);
      }
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

  @SneakyThrows
  private InputFile toInputFile(InputStream inputStream, String filename) {
    return new InputFile(inputStream, filename);
  }

  @SneakyThrows
  private InputMedia toInputPhotoMedia(MultipartFile multipartFile) {
    return toInputPhotoMedia(
        multipartFile.getInputStream(),
        multipartFile.getOriginalFilename()
    );
  }

  @SneakyThrows
  private InputMedia toInputPhotoMedia(InputStream inputStream, String filename) {
    InputMediaPhoto inputMediaPhoto = new InputMediaPhoto();
    inputMediaPhoto.setMedia(inputStream, filename);
    return inputMediaPhoto;
  }

  private void handleUserBlocking(
      String externalChatId,
      TelegramApiRequestException telegramApiRequestException
  ) throws TelegramApiRequestException {
    if (telegramApiRequestException.getMessage().contains(BLOCKING_MESSAGE)) {
      log.info("user with externalChatId '{}' was deleted", externalChatId);
      telegramUserService.deleteByExternalChatId(externalChatId);
    } else {
      throw telegramApiRequestException;
    }
  }

  private void sendPhotoToSpecialChat(
      String caption,
      InputFile photo,
      String externalChatId,
      SendPhoto sendPhoto
  ) throws TelegramApiException {
    sendPhoto.setPhoto(photo);
    sendPhoto.setCaption(caption);
    sendPhoto.setChatId(externalChatId);
    try {
      execute(sendPhoto);
    } catch (TelegramApiRequestException telegramApiRequestException) {
      handleUserBlocking(externalChatId, telegramApiRequestException);
    }
  }

  private void sendFileToChat(
      String caption,
      InputFile file,
      String externalChatId,
      SendDocument sendDocument) throws IOException, TelegramApiException {
    sendDocument.setChatId(externalChatId);
    sendDocument.setDocument(file);
    sendDocument.setCaption(caption);
    try {
      execute(sendDocument);
    } catch (TelegramApiRequestException telegramApiRequestException) {
      handleUserBlocking(externalChatId, telegramApiRequestException);
    }
  }
}
