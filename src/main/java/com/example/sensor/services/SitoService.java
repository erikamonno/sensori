package com.example.sensor.services;

import com.example.sensor.exceptions.SitoEsistenteException;
import com.example.sensor.exceptions.UtenteEsistenteException;
import com.example.sensor.model.dto.SitoDto;
import com.example.sensor.model.request.AddSitoRequest;
import org.springframework.stereotype.Service;

@Service
public interface SitoService {
    SitoDto aggiungiSito(AddSitoRequest request) throws SitoEsistenteException;
}
