package com.service.jwt_auth.service;

import com.service.jwt_auth.pojos.request.AuthRequest;
import com.service.jwt_auth.pojos.request.RegisterRequest;
import com.service.jwt_auth.pojos.response.AuthResponse;
import com.service.jwt_auth.pojos.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerUser(RegisterRequest request);
    AuthResponse auth(AuthRequest request);
}
