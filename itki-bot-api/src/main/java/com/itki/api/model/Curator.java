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
