package com.itki.api.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "[group]")
public class Group {
  @Id
  @GeneratedValue(generator = "group_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(
      name = "group_id_seq",
      sequenceName = "group_id_seq",
      allocationSize = 1
  )
  private Long id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "curator_id")
  private Curator curator;
}
