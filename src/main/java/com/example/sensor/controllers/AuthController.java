package com.example.sensor.controllers;

import com.example.sensor.model.request.LoginRequest;
import com.example.sensor.model.response.LoginResponse;
import com.example.sensor.services.AuthService;
import com.example.sensor.utils.RoleConstants;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return service.login(request);
    }

    @RolesAllowed("USER")
    @GetMapping(path = "/ciao")
    public void ciao() {

    }

}
