package com.itki.api.controller;

import com.itki.api.config.MinioConfig;
import com.itki.api.dto.SendFileToTelegramRequestDto;
import com.itki.api.service.TelegramBroadcastService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
    InputStream photoFromMinio = getFileFromMinio(requestDto.filenames().get(0));
    telegramBroadcastService.sendPhoto(
        requestDto.caption(),
        photoFromMinio,
        requestDto.filenames().get(0)
    );
    return ResponseEntity.ok().build();
  }

  @PostMapping("/broadcast/file")
  @SneakyThrows
  public ResponseEntity<HttpStatus> sendBroadcastFile(
      @RequestBody
      SendFileToTelegramRequestDto requestDto
  ) {
    InputStream fileFromMinio = getFileFromMinio(requestDto.filenames().get(0));
    telegramBroadcastService.sendFile(
        requestDto.caption(),
        fileFromMinio, requestDto.filenames().get(0)
    );
    return ResponseEntity.ok().build();
  }

  @PostMapping("/broadcast/mediaGroup/photo")
  @SneakyThrows
  public ResponseEntity<HttpStatus> sendBroadcastPhotoGroup(
      @RequestBody
      SendFileToTelegramRequestDto requestDto
  ) {
    Map<String, InputStream> filenameInputStreamMap = new HashMap<>();
    for (String filename : requestDto.filenames()) {
      InputStream fileFromMinio = getFileFromMinio(filename);
      filenameInputStreamMap.put(filename, fileFromMinio);
    }
    telegramBroadcastService.sendPhotoMediaGroup(filenameInputStreamMap);
    return ResponseEntity.ok().build();
  }

  private InputStream getFileFromMinio(String filename)
      throws ErrorResponseException,
      InsufficientDataException,
      InternalException,
      InvalidKeyException,
      InvalidResponseException,
      IOException,
      NoSuchAlgorithmException,
      ServerException,
      XmlParserException {
    return minioClient.getObject(
        GetObjectArgs.builder()
            .bucket(MinioConfig.BUCKET_NAME)
            .object(filename)
            .build()
    );
  }
}
