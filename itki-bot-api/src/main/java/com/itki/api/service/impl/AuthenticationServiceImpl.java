package com.itki.api.service.impl;

import com.itki.api.dto.LoginResponseDto;
import com.itki.api.jwt.JwtTokenProvider;
import com.itki.api.model.User;
import com.itki.api.service.AuthenticationService;
import com.itki.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponseDto login(String login, String password) {
        Optional<User> user = userService.findByLogin(login);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new RuntimeException("Wrong login: " + login + " or password");
        }
        return getLoginResponse(user.get());
    }

    private LoginResponseDto getLoginResponse(User user) {
        String accessToken = jwtTokenProvider.createAccessToken(user.getLogin());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(accessToken);
        return loginResponseDto;
    }


}
