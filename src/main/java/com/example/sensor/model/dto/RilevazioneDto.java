package com.example.sensor.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RilevazioneDto {
    private Long id;
    private String stringa;
    private LocalDateTime dataOra;
    private Double valore;
    private String messaggio;
    private SensoreDto sensore;
    private EccezioneDto eccezione;
}
