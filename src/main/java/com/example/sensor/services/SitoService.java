package com.example.sensor.services;

import com.example.sensor.exceptions.SitoEsistenteException;
import com.example.sensor.exceptions.SitoNonEsistenteException;
import com.example.sensor.exceptions.UtenteEsistenteException;
import com.example.sensor.exceptions.UtenteNonEsistenteException;
import com.example.sensor.model.dto.SitoDto;
import com.example.sensor.model.request.AddSitoRequest;
import com.example.sensor.model.request.UpdateSitoRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SitoService {
    SitoDto aggiungiSito(AddSitoRequest request) throws UtenteNonEsistenteException;
    void rimuoviSito(Long id) throws SitoNonEsistenteException;
    void aggiornaSito(UpdateSitoRequest request) throws SitoNonEsistenteException, UtenteNonEsistenteException;
    List<SitoDto> mostraSiti();
    void segnaComeAttivo(Long idUtente, Long idSito) throws UtenteNonEsistenteException, SitoNonEsistenteException;
}
