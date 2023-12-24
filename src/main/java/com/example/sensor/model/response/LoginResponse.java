package com.example.sensor.model.response;

import com.example.sensor.model.dto.UtenteDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    private String token;
    private UtenteDto user;
}
