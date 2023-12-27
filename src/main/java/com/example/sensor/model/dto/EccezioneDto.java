package com.example.sensor.model.dto;

import lombok.Data;

@Data
public class EccezioneDto {
    private Long id;
    private String messaggio;
    private RilevazioneDto rilevazione;
}
