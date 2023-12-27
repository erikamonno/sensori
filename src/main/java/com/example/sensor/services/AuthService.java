package com.example.sensor.services;

import com.example.sensor.model.request.LoginRequest;
import com.example.sensor.model.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
