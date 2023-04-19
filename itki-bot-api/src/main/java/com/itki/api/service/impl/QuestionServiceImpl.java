package com.itki.api.service.impl;

import com.itki.api.model.Question;
import com.itki.api.repository.QuestionRepository;
import com.itki.api.service.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl extends CrudServiceImpl<Question> implements QuestionService {
  public QuestionServiceImpl(QuestionRepository questionRepository) {
    super(questionRepository, Question.class.getSimpleName());
  }
}
