package com.example.sensor.model.mappers;

import com.example.sensor.model.dto.SitoDto;
import com.example.sensor.model.entities.Sito;

public class SitoMapper {

    private static UtenteMapper utenteMapper = new UtenteMapper();

    public SitoDto convertEntityToDto(Sito entity) {
        SitoDto dto = null;
        if(entity != null) {
            dto = new SitoDto();
            dto.setId(entity.getId());
            dto.setNome(entity.getNome());
            dto.setCitta(entity.getCitta());
            dto.setIndirizzo(entity.getIndirizzo());
            dto.setCliente(utenteMapper.convertEntityToDto(entity.getCliente()));
        }
        return dto;
    }
}
