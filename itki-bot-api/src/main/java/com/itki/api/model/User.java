package com.itki.api.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "[user]")
public class User {
  @Id
  @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(
    name = "user_id_seq",
    sequenceName = "user_id_seq",
    allocationSize = 1
  )
  private Long id;
  private String login;
  private String password;
  @ManyToMany
  @JoinTable(
    name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;
}
