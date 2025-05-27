package com.cine.repository;

import com.cine.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    // También tiene count() automático
}
