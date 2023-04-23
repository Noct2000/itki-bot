package com.itki.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface TelegramBroadcastService {
  void sendFile(String caption, MultipartFile file);

  void sendPhoto(String caption, MultipartFile photo);

  void sendTextMessage(String text);
}
