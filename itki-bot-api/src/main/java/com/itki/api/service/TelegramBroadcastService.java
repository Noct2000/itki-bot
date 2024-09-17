package com.itki.api.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

public interface TelegramBroadcastService {
  void sendFile(String caption, MultipartFile file);

  void sendPhoto(String caption, MultipartFile photo);

  void sendPhoto(String caption, InputStream photo, String filename);

  void sendTextMessage(String text);

  void sendPhotoMediaGroup(MultipartFile[] photos);
}
