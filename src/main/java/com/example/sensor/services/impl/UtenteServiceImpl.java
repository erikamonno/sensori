package com.example.sensor.services.impl;

import com.example.sensor.exceptions.UtenteEsistenteException;
import com.example.sensor.exceptions.UtenteNonEsistenteException;
import com.example.sensor.model.dto.UtenteDto;
import com.example.sensor.model.entities.Utente;
import com.example.sensor.model.mappers.UtenteMapper;
import com.example.sensor.model.request.AddUserRequest;
import com.example.sensor.model.request.UpdateRequest;
import com.example.sensor.repositories.UtenteRepository;
import com.example.sensor.services.UtenteService;
import com.example.sensor.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtenteServiceImpl implements UtenteService {

    private static final UtenteMapper mapper = new UtenteMapper();
    @Autowired
    UtenteRepository repository;
    @Override
    public UtenteDto aggiungiUtente(AddUserRequest request) throws UtenteEsistenteException {
        if (repository.existsByUsername(request.getUsername())) {
            throw new UtenteEsistenteException();
        }
        Utente entity = new Utente();
        entity.setUsername(request.getUsername());
        entity.setPassword(PasswordUtil.encodePassword(request.getPassword()));
        entity.setNome(request.getNome());
        entity.setCognome(request.getCognome());
        entity.setAdmin(request.getAdmin());
        entity = repository.save(entity);
        return mapper.convertEntityToDto(entity);
    }

    @Override
    public void eliminaUtente(Long id) throws UtenteNonEsistenteException {
        if(!repository.existsById(id)) {
            throw new UtenteNonEsistenteException();
        }else {
            repository.deleteById(id);
        }
    }

    @Override
    public void aggiornaUtente(UpdateRequest request) throws UtenteNonEsistenteException {
        Optional<Utente> oEntity = repository.findById(request.getId());
        if(oEntity.isEmpty()) {
            throw new UtenteNonEsistenteException();
        }
        Utente entity = oEntity.get();
        entity.setNome(request.getNome());
        entity.setCognome(request.getCognome());
        entity.setAdmin(request.getAdmin());
        entity = repository.save(entity);
    }

    @Override
    public List<UtenteDto> recuperaUtenti() {
        return repository.findAll().stream()
                .map(utente -> mapper.convertEntityToDto(utente))
                .toList();
    }
}
