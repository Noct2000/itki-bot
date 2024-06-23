package com.itki.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role implements Serializable {
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
