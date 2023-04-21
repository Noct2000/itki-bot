package com.itki.api.dto;

import lombok.Data;

@Data
public class CuratorResponseDto {
  private Long id;
  private String name;
  private String lastName;
  private String additionalName;
  private String department;
  private String position;
}
