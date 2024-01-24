package com.example.sensor.controllers;

import com.example.sensor.exceptions.SitoEsistenteException;
import com.example.sensor.exceptions.SitoNonEsistenteException;
import com.example.sensor.exceptions.UtenteEsistenteException;
import com.example.sensor.exceptions.UtenteNonEsistenteException;
import com.example.sensor.model.dto.SitoDto;
import com.example.sensor.model.entities.Sito;
import com.example.sensor.model.request.AddSitoRequest;
import com.example.sensor.model.request.UpdateSitoRequest;
import com.example.sensor.services.SitoService;
import com.example.sensor.utils.RoleConstants;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/sito")
public class SitoController {
    @Autowired
    SitoService service;
    @RolesAllowed(value = RoleConstants.ADMIN)
    @PostMapping(path = "/aggiungi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SitoDto> aggiungiSito(@Valid @RequestBody AddSitoRequest request) {
        ResponseEntity<SitoDto> response = null;
        try {
            SitoDto dto = service.aggiungiSito(request);
            response = new ResponseEntity<>(dto, HttpStatus.OK);
        }catch(UtenteNonEsistenteException e){
            response = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }

    @RolesAllowed(value = RoleConstants.ADMIN)
    @DeleteMapping(path = "/elimina/{id}")
    public ResponseEntity<Void> rimuoviSito(@Valid @PathVariable("id") Long id) {
        ResponseEntity<Void> response = null;
        try {
            service.rimuoviSito(id);
            response = new ResponseEntity<>(HttpStatus.OK);
        }catch (SitoNonEsistenteException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RolesAllowed(value = RoleConstants.ADMIN)
    @PutMapping(path = "/aggiorna")
    public ResponseEntity<Void> aggiornaSito(@Valid @RequestBody UpdateSitoRequest request) {
        ResponseEntity<Void> response = null;
        try {
           service.aggiornaSito(request);
           response = new ResponseEntity<>(HttpStatus.OK);
        }catch (SitoNonEsistenteException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (UtenteNonEsistenteException e) {
            response = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }

    @RolesAllowed(value = RoleConstants.ADMIN)
    @GetMapping(path = "/mostra-siti", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SitoDto> mostraUtenti() {
        return service.mostraSiti();
    }

    @RolesAllowed(value = { RoleConstants.ADMIN, RoleConstants.USER })
    @PutMapping(path = "/imposta-attivo/{idSito}/{idUtente}")
    public ResponseEntity<SitoDto> segnaComeAttivo(@Valid @PathVariable("idSito") Long idSito, @PathVariable("idUtente") Long idUtente) {
        ResponseEntity<SitoDto> response = null;
        try {
            service.segnaComeAttivo(idUtente,idSito);
            response = new ResponseEntity<>(HttpStatus.OK);
        }catch(UtenteNonEsistenteException | SitoNonEsistenteException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
