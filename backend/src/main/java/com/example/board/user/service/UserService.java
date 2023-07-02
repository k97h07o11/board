package com.example.board.user.service;

import com.example.board.user.dto.JoinRequestDto;
import com.example.board.user.entity.User;
import com.example.board.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(JoinRequestDto joinRequestDto) {
        if (!validateUsername(joinRequestDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User user = joinRequestDto.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean validateUsername(String username) {
        return !userRepository.existsByUsername(username);
    }
}
