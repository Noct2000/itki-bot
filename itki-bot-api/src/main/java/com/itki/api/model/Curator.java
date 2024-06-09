package com.itki.api.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "curator")
public class Curator {
  @Id
  @GeneratedValue(generator = "curator_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(
      name = "curator_id_seq",
      sequenceName = "curator_id_seq",
      allocationSize = 1
  )
  private Long id;
  private String name;
  private String lastName;
  private String additionalName;
  private String department;
  private String position;
}
