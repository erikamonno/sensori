package com.example.sensor.model.request;

import com.example.sensor.model.dto.SitoDto;
import com.example.sensor.model.entities.Sito;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
public class UpdateRequest {
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
