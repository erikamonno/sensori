package com.example.sensor.services.impl;

import com.example.sensor.exceptions.SitoEsistenteException;
import com.example.sensor.exceptions.UtenteEsistenteException;
import com.example.sensor.model.dto.SitoDto;
import com.example.sensor.model.entities.Sito;
import com.example.sensor.model.mappers.SitoMapper;
import com.example.sensor.model.mappers.UtenteMapper;
import com.example.sensor.model.request.AddSitoRequest;
import com.example.sensor.repositories.SitoRepository;
import com.example.sensor.repositories.UtenteRepository;
import com.example.sensor.services.SitoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class SitoServiceImpl implements SitoService {

    @Autowired
    SitoRepository repository;
    @Autowired
    UtenteRepository utenteRepository;
    private static SitoMapper mapper = new SitoMapper();
    @Override
    public SitoDto aggiungiSito(AddSitoRequest request) throws SitoEsistenteException {
        log.info("Aggiungi sito: {}", request);
        if(repository.existsByNome(request.getNome())) {
            throw new SitoEsistenteException();
        }
        Sito entity = new Sito();
        entity.setNome(request.getNome());
        entity.setCitta(request.getCitta());
        entity.setIndirizzo(request.getIndirizzo());
        entity.setCliente(utenteRepository.getReferenceById(request.getIdCliente()));
        repository.save(entity);
        return mapper.convertEntityToDto(entity);
    }
}
