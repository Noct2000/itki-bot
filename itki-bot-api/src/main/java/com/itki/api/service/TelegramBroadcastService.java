package com.itki.api.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.Map;

public interface TelegramBroadcastService {
  void sendFile(String caption, MultipartFile file);

  void sendFile(String caption, InputStream file, String filename);

  void sendPhoto(String caption, MultipartFile photo);

  void sendPhoto(String caption, InputStream photo, String filename);

  void sendTextMessage(String text);

  void sendPhotoMediaGroup(MultipartFile[] photos);

  void sendPhotoMediaGroup(Map<String, InputStream> filenameInputStreamMap);
}
