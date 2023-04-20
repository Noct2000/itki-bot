package com.itki.api.repository;

import com.itki.api.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
  Optional<TelegramUser> findTelegramUserByExternalChatId(String externalChatId);

  @Query(value = "select tu.externalChatId from TelegramUser tu")
  List<String> getAllExternalChatIds();
}
