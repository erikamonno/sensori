package com.example.sensor.controllers;

import com.example.sensor.exceptions.SitoEsistenteException;
import com.example.sensor.exceptions.UtenteEsistenteException;
import com.example.sensor.model.dto.SitoDto;
import com.example.sensor.model.entities.Sito;
import com.example.sensor.model.request.AddSitoRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
        }catch(SitoEsistenteException e){
            response = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }
}
