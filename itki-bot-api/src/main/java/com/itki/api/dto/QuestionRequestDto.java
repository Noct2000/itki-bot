package com.itki.api.dto;

import lombok.Data;

@Data
public class QuestionRequestDto {
  private String questionText;
  private String answerText;
}
