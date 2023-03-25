package com.itki.api.service;

import com.itki.api.model.User;

import java.util.Optional;

public interface UserService extends CrudService<User> {
    Optional<User> findByLogin(String login);
}
