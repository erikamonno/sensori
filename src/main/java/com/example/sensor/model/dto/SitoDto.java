package com.example.sensor.model.dto;

import com.example.sensor.model.entities.Utente;
import lombok.Data;

@Data
public class SitoDto {

    private Long id;
    private String nome;
    private String città;
    private String indirizzo;
    private UtenteDto cliente;

}
