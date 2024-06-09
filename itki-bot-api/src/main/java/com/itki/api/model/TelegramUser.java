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
