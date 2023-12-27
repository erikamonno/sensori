package com.example.sensor.model.dto;

import lombok.Data;

@Data
public class SitoDto {

    private Long id;
    private String nome;
    private String città;
    private String indirizzo;
    private UtenteDto cliente;

}
