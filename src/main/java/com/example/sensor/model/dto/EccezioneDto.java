package com.example.sensor.model.dto;

import com.example.sensor.model.entities.Rilevazione;
import lombok.Data;

@Data
public class EccezioneDto {
    private Long id;
    private String messaggio;
    private RilevazioneDto rilevazione;
}
