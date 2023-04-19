package com.itki.api.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "answer")
public class Answer {
  @Id
  @GeneratedValue(generator = "answer_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(
      name = "answer_id_seq",
      sequenceName = "answer_id_seq",
      allocationSize = 1
  )
  private Long id;
  private String text;
}
