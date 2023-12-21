package com.example.sensor.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sensori")
public class Sensore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "marca")
    private String marca;

    @Column(name = "codice_errore")
    private String codiceErrore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sito")
    private Sito sito;
}
