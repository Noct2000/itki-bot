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

@RestController
@RequestMapping("/tg/v2")
@RequiredArgsConstructor
public class TelegramFileMessagesController {
  private final TelegramBroadcastService telegramBroadcastService;
  private final MinioClient minioClient;

  @PostMapping("/broadcast/photo")
  @SneakyThrows
  public ResponseEntity<HttpStatus> sendBroadcastPhoto(
      @RequestBody
      SendFileToTelegramRequestDto requestDto
  ) {
    InputStream photoFromMinio = minioClient.getObject(
        GetObjectArgs.builder()
            .bucket(MinioConfig.BUCKET_NAME)
            .object(requestDto.filenames().get(0))
            .build()
    );
    telegramBroadcastService.sendPhoto(
        requestDto.caption(),
        photoFromMinio,
        requestDto.filenames().get(0)
    );
    return ResponseEntity.ok().build();
  }
}
