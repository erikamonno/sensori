package com.example.sensor.controllers;

import com.example.sensor.exceptions.UtenteEsistenteException;
import com.example.sensor.exceptions.UtenteNonEsistenteException;
import com.example.sensor.model.dto.UtenteDto;
import com.example.sensor.model.request.AddUserRequest;
import com.example.sensor.model.request.UpdateUserRequest;
import com.example.sensor.services.UtenteService;
import com.example.sensor.utils.RoleConstants;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    UtenteService service;

    @RolesAllowed(value = RoleConstants.ADMIN)
    @PostMapping(path = "/aggiungi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UtenteDto> aggiungiUtente(@Valid @RequestBody AddUserRequest request) {
        ResponseEntity<UtenteDto> response = null;
        try {
            UtenteDto utenteDto = service.aggiungiUtente(request);
            response = new ResponseEntity<>(utenteDto, HttpStatus.OK);
        }catch (UtenteEsistenteException e) {
            response = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }
    @RolesAllowed(value = RoleConstants.ADMIN)
    @DeleteMapping(path = "/rimuovi/{id}")
    public ResponseEntity<Void> eliminaUtente(@Valid @PathVariable("id") Long id) {
        ResponseEntity<Void> response = null;
        try {
             service.eliminaUtente(id);
             response = new ResponseEntity<>(HttpStatus.OK);

        }catch (UtenteNonEsistenteException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RolesAllowed(value = RoleConstants.ADMIN)
    @PutMapping(path = "/aggiorna", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> aggiornaUtente(@Valid @RequestBody UpdateUserRequest request) {
        ResponseEntity<Void> response = null;
        try{
            service.aggiornaUtente(request);
            response = new ResponseEntity<>(HttpStatus.OK);
        }catch (UtenteNonEsistenteException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RolesAllowed(value = RoleConstants.ADMIN)
    @GetMapping(path = "restituisci", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UtenteDto> recuperaAutori() {
        return service.recuperaUtenti();
    }
}
