package com.itki.api.service.impl;

import com.itki.api.model.User;
import com.itki.api.repository.UserRepository;
import com.itki.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
