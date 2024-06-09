package com.itki.api.controller;

import com.itki.api.service.TelegramBroadcastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/tg")
@RequiredArgsConstructor
public class TelegramController {
  private final TelegramBroadcastService telegramBroadcastService;

  @PostMapping("/broadcast/file")
  public ResponseEntity<HttpStatus> sendBroadcastFile(
      @RequestParam("document") MultipartFile multipartFile,
      @RequestParam String caption
  ) {
    telegramBroadcastService.sendFile(caption, multipartFile);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/broadcast/photo")
  public ResponseEntity<HttpStatus> sendBroadcastPhoto(
      @RequestParam("photo") MultipartFile multipartFile,
      @RequestParam String caption
  ) {
    telegramBroadcastService.sendPhoto(caption, multipartFile);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/broadcast/text")
  public ResponseEntity<HttpStatus> sendBroadcastText(
      @RequestBody String text
  ) {
    telegramBroadcastService.sendTextMessage(text);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/broadcast/mediaGroup/photo")
  public ResponseEntity<HttpStatus> sendBroadcastPhotoGroup(
      @RequestParam("photo") MultipartFile[] multipartFiles
  ) {
    telegramBroadcastService.sendPhotoMediaGroup(multipartFiles);
    return ResponseEntity.ok().build();
  }
}
