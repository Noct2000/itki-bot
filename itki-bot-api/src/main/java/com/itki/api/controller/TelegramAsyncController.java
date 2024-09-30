package com.itki.api.controller;

import com.itki.api.config.MinioConfig;
import com.itki.api.dto.SendFileToTelegramRequestDto;
import com.itki.api.service.TelegramBroadcastService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/tg/v2")
@RequiredArgsConstructor
public class TelegramAsyncController {
  private final TelegramBroadcastService telegramBroadcastService;
  private final MinioClient minioClient;
  private final ExecutorService executorService;

  @PostMapping("/broadcast/photo")
  public ResponseEntity<HttpStatus> sendBroadcastPhoto(
      @RequestBody
      SendFileToTelegramRequestDto requestDto
  ) {
    executorService.execute(() -> {
      InputStream photoFromMinio = getFileFromMinio(requestDto.filenames().get(0));
      telegramBroadcastService.sendPhoto(
          requestDto.caption(),
          photoFromMinio,
          requestDto.filenames().get(0)
      );
    });
    return ResponseEntity.ok().build();
  }

  @PostMapping("/broadcast/file")
  @SneakyThrows
  public ResponseEntity<HttpStatus> sendBroadcastFile(
      @RequestBody
      SendFileToTelegramRequestDto requestDto
  ) {
    executorService.execute(() -> {
      InputStream fileFromMinio = getFileFromMinio(requestDto.filenames().get(0));
      telegramBroadcastService.sendFile(
          requestDto.caption(),
          fileFromMinio, requestDto.filenames().get(0)
      );
    });
    return ResponseEntity.ok().build();
  }

  @PostMapping("/broadcast/mediaGroup/photo")
  @SneakyThrows
  public ResponseEntity<HttpStatus> sendBroadcastPhotoGroup(
      @RequestBody
      SendFileToTelegramRequestDto requestDto
  ) {
    executorService.execute(() -> {
      Map<String, InputStream> filenameInputStreamMap = new HashMap<>();
      for (String filename : requestDto.filenames()) {
        InputStream fileFromMinio = getFileFromMinio(filename);
        filenameInputStreamMap.put(filename, fileFromMinio);
      }
      telegramBroadcastService.sendPhotoMediaGroup(filenameInputStreamMap);
    });
    return ResponseEntity.ok().build();
  }

  @PostMapping("/broadcast/text")
  public ResponseEntity<HttpStatus> sendBroadcastText(
      @RequestBody String text
  ) {
    executorService.execute(() -> {
      telegramBroadcastService.sendTextMessage(text);
    });
    return ResponseEntity.ok().build();
  }

  private InputStream getFileFromMinio(String filename) {
    try {
      return minioClient.getObject(
          GetObjectArgs.builder()
              .bucket(MinioConfig.BUCKET_NAME)
              .object(filename)
              .build()
      );
    } catch (Exception e) {
      throw new RuntimeException("Error getting file"
          + filename
          + " from Minio", e);
    }
  }
}
