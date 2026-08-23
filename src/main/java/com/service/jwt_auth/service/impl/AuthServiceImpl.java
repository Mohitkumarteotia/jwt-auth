package com.service.jwt_auth.service.impl;

import com.service.jwt_auth.entity.User;
import com.service.jwt_auth.exception.custom.InvalidCredentialsException;
import com.service.jwt_auth.exception.custom.UserNotFoundException;
import com.service.jwt_auth.pojos.request.AuthRequest;
import com.service.jwt_auth.pojos.request.RegisterRequest;
import com.service.jwt_auth.pojos.response.AuthResponse;
import com.service.jwt_auth.pojos.response.RegisterResponse;
import com.service.jwt_auth.repository.UserRepository;
import com.service.jwt_auth.service.AuthService;
import com.service.jwt_auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public RegisterResponse registerUser(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return RegisterResponse.builder()
                .name(user.getName())
                .build();
    }

    @Override
    public AuthResponse auth(AuthRequest request) {
        User user = userRepository.findByName(request.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        String token = jwtUtil.generateToken(user.getName());
        return AuthResponse.builder()
                .name(user.getName())
                .token(token)
                .build();
    }

}
