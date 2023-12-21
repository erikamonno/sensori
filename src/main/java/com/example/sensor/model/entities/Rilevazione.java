package com.example.sensor.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rilevazioni")
public class Rilevazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "stringa")
    private String stringa;

    @Column(name = "dataora")
    private LocalDateTime dataOra;

    @Column(name = "valore")
    private Double valore;

    @Column(name = "messaggio")
    private String messaggio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sensore")
    private Sensore sensore;
}
