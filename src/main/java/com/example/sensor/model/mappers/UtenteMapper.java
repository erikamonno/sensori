package com.example.sensor.model.mappers;

import com.example.sensor.model.dto.UtenteDto;
import com.example.sensor.model.entities.Sito;
import com.example.sensor.model.entities.Utente;

public class UtenteMapper {

    public UtenteDto convertEntityToDto(Utente entity) {
        UtenteDto dto = null;
        if (entity != null) {
            dto = new UtenteDto();
            dto.setId(entity.getId());
            dto.setUsername(entity.getUsername());
            dto.setPassword(entity.getPassword());
            dto.setNome(entity.getNome());
            dto.setCognome(entity.getCognome());
            dto.setAdmin(entity.getAdmin());
            dto.setSitoAttivo(new SitoMapper().convertEntityToDto(entity.getSitoAttivo()));
        }
        return dto;
    }
}
