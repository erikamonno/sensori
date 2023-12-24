package com.example.sensor.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "admin")
    private Boolean admin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sito_attivo")
    private Sito sitoAttivo;
}
