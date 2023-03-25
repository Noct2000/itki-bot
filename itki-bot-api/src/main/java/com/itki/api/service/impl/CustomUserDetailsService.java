package com.itki.api.service.impl;

import com.itki.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<com.itki.api.model.User> user = userService.findByLogin(username);
        if (user.isPresent()) {
            User.UserBuilder userBuilder = User.withUsername(username);
            userBuilder.password(user.get().getPassword());
            userBuilder.roles(user.get().getRoles().stream()
                    .map(role -> role.getName().name())
                    .toArray(String[]::new));
            return userBuilder.build();
        }
        throw new RuntimeException("Wrong login or password from user: " + username);
    }
}
