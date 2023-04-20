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
@Table(name = "telegram_user")
public class TelegramUser {
  @Id
  @GeneratedValue(generator = "telegram_user_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(
      name = "telegram_user_id_seq",
      sequenceName = "telegram_user_id_seq",
      allocationSize = 1
  )
  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String externalChatId;
}
