package com.example.sensor.services.impl;

import com.example.sensor.exceptions.SitoEsistenteException;
import com.example.sensor.exceptions.SitoNonEsistenteException;
import com.example.sensor.exceptions.UtenteEsistenteException;
import com.example.sensor.exceptions.UtenteNonEsistenteException;
import com.example.sensor.model.dto.SitoDto;
import com.example.sensor.model.entities.Sito;
import com.example.sensor.model.entities.Utente;
import com.example.sensor.model.mappers.SitoMapper;
import com.example.sensor.model.mappers.UtenteMapper;
import com.example.sensor.model.request.AddSitoRequest;
import com.example.sensor.model.request.UpdateSitoRequest;
import com.example.sensor.repositories.SitoRepository;
import com.example.sensor.repositories.UtenteRepository;
import com.example.sensor.services.SitoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class SitoServiceImpl implements SitoService {

    @Autowired
    SitoRepository repository;
    @Autowired
    UtenteRepository utenteRepository;
    private static SitoMapper mapper = new SitoMapper();
    private static UtenteMapper utenteMapper = new UtenteMapper();
    @Override
    public SitoDto aggiungiSito(AddSitoRequest request) throws UtenteNonEsistenteException {
        log.info("Aggiungi sito: {}", request);
        Sito entity = new Sito();
        entity.setNome(request.getNome());
        entity.setCitta(request.getCitta());
        entity.setIndirizzo(request.getIndirizzo());
        if(request.getIdCliente() != null) {
            Optional<Utente> optional = utenteRepository.findById(request.getIdCliente());
            if(optional.isEmpty()) {
                throw new UtenteNonEsistenteException();
            }
            entity.setCliente(optional.get());
        }
        repository.save(entity);
        return mapper.convertEntityToDto(entity);
    }

    @Override
    public void rimuoviSito(Long id) throws SitoNonEsistenteException {
        if(!repository.existsById(id)) {
            throw new SitoNonEsistenteException();
        }else {
            repository.deleteById(id);
        }
    }

    @Override
    public void aggiornaSito(UpdateSitoRequest request) throws SitoNonEsistenteException, UtenteNonEsistenteException {
        Optional<Sito> oEntity = repository.findById(request.getId());
        if(oEntity.isEmpty()) {
            throw new SitoNonEsistenteException();
        }
        Sito entity = oEntity.get();
        entity.setId(request.getId());
        entity.setNome(request.getNome());
        entity.setCitta(request.getCitta());
        entity.setIndirizzo(request.getIndirizzo());
        if(request.getIdCliente() != null) {
            Optional<Utente> oCliente = utenteRepository.findById(request.getIdCliente());
            if(oCliente.isEmpty()) {
                throw new UtenteNonEsistenteException();
            }
            entity.setCliente(oCliente.get());
        }
        repository.save(entity);
    }

    @Override
    public List<SitoDto> mostraSiti() {
        return repository.findAll().stream()
                .map(sito -> mapper.convertEntityToDto(sito)).toList();
    }

    @Override
    public void segnaComeAttivo(Long idUtente, Long idSito) throws UtenteNonEsistenteException, SitoNonEsistenteException {
        Optional<Utente> oUtente = utenteRepository.findById(idUtente);
        if(oUtente.isEmpty()) {
            throw new UtenteNonEsistenteException();
        }
        Optional<Sito> oSito = repository.findById(idSito);
        if(oSito.isEmpty()) {
            throw new SitoNonEsistenteException();
        }
        Utente entityUtente = oUtente.get();
        entityUtente.setSitoAttivo(oSito.get());
        utenteRepository.save(entityUtente);
    }


}
