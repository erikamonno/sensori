package com.example.sensor.services;

import com.example.sensor.exceptions.UtenteEsistenteException;
import com.example.sensor.exceptions.UtenteNonEsistenteException;
import com.example.sensor.model.dto.UtenteDto;
import com.example.sensor.model.entities.Utente;
import com.example.sensor.model.request.AddUserRequest;
import com.example.sensor.model.request.UpdateRequest;

import java.util.List;

public interface UtenteService {

    UtenteDto aggiungiUtente(AddUserRequest request) throws UtenteEsistenteException;
    void eliminaUtente(Long id) throws UtenteNonEsistenteException;
    void aggiornaUtente(UpdateRequest request) throws UtenteNonEsistenteException;
    List<UtenteDto> recuperaUtenti();
}
