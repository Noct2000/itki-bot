package com.itki.api.repository;

import com.itki.api.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("select u from User u join fetch u.roles where u.login = :login")
  Optional<User> findUserByLogin(@Param("login") String login);
}
