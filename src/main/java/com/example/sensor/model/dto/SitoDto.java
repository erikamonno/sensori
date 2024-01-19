package com.example.sensor.model.dto;

import lombok.Data;

@Data
public class SitoDto {

    private Long id;
    private String nome;
    private String citta;
    private String indirizzo;
    private UtenteDto cliente;

}
