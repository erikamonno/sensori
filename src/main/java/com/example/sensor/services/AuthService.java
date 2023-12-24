package com.example.sensor.services;

import com.example.sensor.model.request.LoginRequest;
import com.example.sensor.model.response.LoginResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    LoginResponse login(LoginRequest request);
}
