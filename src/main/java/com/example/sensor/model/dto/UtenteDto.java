package com.example.sensor.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UtenteDto {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String nome;
    private String cognome;
    private Boolean admin;
    private SitoDto sitoAttivo;
}
