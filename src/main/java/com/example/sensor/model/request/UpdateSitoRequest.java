package com.example.sensor.model.request;

import com.example.sensor.model.entities.Utente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateSitoRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String citta;
    @NotBlank
    private String indirizzo;
    @NotNull
    private Long idCliente;
}
