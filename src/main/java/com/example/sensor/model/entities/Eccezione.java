package com.example.sensor.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "eccezioni")
public class Eccezione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "messaggio")
    private String messaggio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rilevazione")
    private Rilevazione rilevazione;
}
