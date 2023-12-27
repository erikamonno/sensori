package com.example.sensor.services.impl;

import com.example.sensor.model.dto.UtenteDto;
import com.example.sensor.model.entities.Utente;
import com.example.sensor.model.request.LoginRequest;
import com.example.sensor.model.response.LoginResponse;
import com.example.sensor.repositories.UtenteRepository;
import com.example.sensor.services.AuthService;
import com.example.sensor.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public LoginResponse login(LoginRequest request) {
        log.info("login for user {}", request.getUsername());
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Utente user = utenteRepository.findByUsername(authentication.getName()).orElseThrow();

        return LoginResponse.builder()
                .user(convertEntityToDto(user))
                .token(jwtUtil.createToken(user))
                .build();
    }

    public enum Genere {MASCHIO, FEMMINA, TRANS, SADAS}

    private UtenteDto convertEntityToDto(Utente entity) {
        UtenteDto dto = null;
        if(entity != null) {
            dto = new UtenteDto();
            dto.setId(entity.getId());
            dto.setNome(entity.getNome());
            dto.setCognome(entity.getCognome());
            dto.setUsername(entity.getUsername());
            dto.setAdmin(entity.getAdmin());
        }
        return dto;
    }
}
