package com.itki.api.dto;

import lombok.Data;

@Data
public class GroupResponseDto {
  private Long id;
  private String name;
  private CuratorResponseDto curatorResponseDto;
}
