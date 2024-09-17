package com.itki.api.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
  public static final String BUCKET_NAME = "my-data";
  @Value("${MINIO_ACCESS_KEY}")
  private String accessKey;
  @Value("${MINIO_SECRET_KEY}")
  private String secretKey;
  @Value("${MINIO_URL}")
  private String minioUrl;

  @Bean
  public MinioClient minioClient() {
    return MinioClient.builder()
        .endpoint(minioUrl)
        .credentials(accessKey, secretKey)
        .build();
  }
}
