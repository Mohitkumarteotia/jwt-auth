package com.service.jwt_auth.controller;

import com.service.jwt_auth.pojos.request.AuthRequest;
import com.service.jwt_auth.pojos.request.RegisterRequest;
import com.service.jwt_auth.pojos.response.AuthResponse;
import com.service.jwt_auth.pojos.response.RegisterResponse;
import com.service.jwt_auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.auth(request);
        return ResponseEntity.ok(response);
    }
}