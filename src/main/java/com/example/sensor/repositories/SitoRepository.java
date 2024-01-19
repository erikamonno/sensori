package com.example.sensor.repositories;

import com.example.sensor.model.entities.Sito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitoRepository extends JpaRepository<Sito, Long> {
    Boolean existsByNome(String nome);
}
