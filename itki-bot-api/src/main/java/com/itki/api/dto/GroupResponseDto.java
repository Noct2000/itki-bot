package com.itki.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GroupResponseDto {
  private Long id;
  private String name;
  @JsonProperty("curator")
  private CuratorResponseDto curatorResponseDto;
}
