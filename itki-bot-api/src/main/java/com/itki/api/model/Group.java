package com.itki.api.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
