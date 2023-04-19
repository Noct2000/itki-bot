package com.itki.api.service.impl;

import com.itki.api.model.Answer;
import com.itki.api.repository.AnswerRepository;
import com.itki.api.service.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl extends CrudServiceImpl<Answer> implements AnswerService {
  public AnswerServiceImpl(AnswerRepository answerRepository) {
    super(answerRepository, Answer.class.getSimpleName());
  }
}
