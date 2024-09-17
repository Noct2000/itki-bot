package com.itki.api.controller;

import com.itki.api.config.MinioConfig;
import com.itki.api.dto.UploadResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
@RequestMapping("/minio")
@RequiredArgsConstructor
public class UploadToMinioController {
  private final MinioClient minioClient;

  @PostMapping("/upload")
  @SneakyThrows
  public ResponseEntity<UploadResponse> uploadToMinio(@RequestHeader MultipartFile file) {
    String filename = String.format(
        "[%d] %s",
        System.currentTimeMillis(),
        file.getOriginalFilename()
    );
    PutObjectArgs.Builder putObjectArgs = PutObjectArgs.builder()
        .bucket(MinioConfig.BUCKET_NAME)
        .object(filename)
        .stream(file.getInputStream(), file.getSize(), -1);
    minioClient.putObject(putObjectArgs.build());
    return ResponseEntity.ok().body(new UploadResponse(List.of(filename)));
  }
}
