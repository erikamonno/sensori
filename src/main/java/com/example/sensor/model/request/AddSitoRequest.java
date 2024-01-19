package com.example.sensor.model.request;

import com.example.sensor.model.dto.UtenteDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddSitoRequest {
    @NotBlank
    private String nome;
    private String citta;
    private String indirizzo;
    private Long idCliente;
}
