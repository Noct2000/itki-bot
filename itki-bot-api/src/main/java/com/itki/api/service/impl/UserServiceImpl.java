package com.itki.api.service.impl;

import com.itki.api.model.User;
import com.itki.api.repository.UserRepository;
import com.itki.api.service.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService {
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    super(userRepository, User.class.getSimpleName());
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> findByLogin(String login) {
    return userRepository.findUserByLogin(login);
  }
}
