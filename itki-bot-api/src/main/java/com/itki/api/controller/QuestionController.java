package com.itki.api.controller;

import com.itki.api.dto.QuestionRequestDto;
import com.itki.api.dto.QuestionResponseDto;
import com.itki.api.mapper.QuestionDtoMapper;
import com.itki.api.model.Question;
import com.itki.api.service.AnswerService;
import com.itki.api.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
  private final QuestionService questionService;
  private final AnswerService answerService;
  private final QuestionDtoMapper questionDtoMapper;

  @GetMapping
  public List<QuestionResponseDto> getAll() {
    return questionService.findAll()
        .stream()
        .map(questionDtoMapper::toResponse)
        .collect(Collectors.toList());
  }

  @PostMapping
  public QuestionResponseDto save(@RequestBody QuestionRequestDto questionRequestDto) {
    Question question = questionDtoMapper.toModel(questionRequestDto);
    answerService.save(question.getAnswer());
    questionService.save(question);
    return questionDtoMapper.toResponse(question);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
    Question question = questionService.findById(id);
    questionService.deleteById(question.getId());
    answerService.deleteById(question.getAnswer().getId());
    return ResponseEntity.ok().build();
  }
}
