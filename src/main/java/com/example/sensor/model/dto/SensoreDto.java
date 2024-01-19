package com.example.sensor.model.dto;

import lombok.Data;

@Data
public class SensoreDto {
    private Long id;
    private String tipo;
    private String marca;
    private SitoDto sito;
}
