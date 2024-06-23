package com.itki.api.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question implements Serializable {
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
