package com.itki.api.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
  @Id
  @GeneratedValue(generator = "question_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(
      name = "question_id_seq",
      sequenceName = "question_id_seq",
      allocationSize = 1
  )
  private Long id;
  private String text;
  @OneToOne
  private Answer answer;
}
