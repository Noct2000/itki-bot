package com.itki.api.mapper;

import com.itki.api.dto.QuestionRequestDto;
import com.itki.api.dto.QuestionResponseDto;
import com.itki.api.model.Answer;
import com.itki.api.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionDtoMapper {
  public Question toModel(QuestionRequestDto questionRequestDto) {
    Question question = new Question();
    question.setText(questionRequestDto.getQuestionText());
    Answer answer = new Answer();
    answer.setText(questionRequestDto.getAnswerText());
    question.setAnswer(answer);
    return question;
  }

  public QuestionResponseDto toResponse(Question question) {
    QuestionResponseDto questionResponseDto = new QuestionResponseDto();
    questionResponseDto.setQuestionId(question.getId());
    questionResponseDto.setAnswerId(question.getAnswer().getId());
    questionResponseDto.setQuestionText(question.getText());
    questionResponseDto.setAnswerText(question.getAnswer().getText());
    return questionResponseDto;
  }
}
