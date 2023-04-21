package com.itki.api.dto;

import lombok.Data;

@Data
public class QuestionResponseDto {
  private Long questionId;
  private Long answerId;
  private String questionText;
  private String answerText;
}
