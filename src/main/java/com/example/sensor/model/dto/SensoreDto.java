package com.example.sensor.model.dto;

import com.example.sensor.model.entities.Sito;
import lombok.Data;

@Data
public class SensoreDto {
    private Long id;
    private String tipo;
    private String marca;
    private String codiceErrore;
    private SitoDto sito;
}
