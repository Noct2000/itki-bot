package com.itki.api.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
  @Id
  @GeneratedValue(generator = "role_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(
      name = "role_id_seq",
      sequenceName = "role_id_seq",
      allocationSize = 1
  )
  private Long id;
  @Enumerated(EnumType.STRING)
  private RoleName name;

  public enum RoleName {
    ADMIN, USER
  }
}
