package com.example.sensor.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotNull
    private Boolean admin;
    private Long idSitoAttivo;
}
