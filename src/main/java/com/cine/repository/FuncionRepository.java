package com.cine.repository;

import com.cine.model.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionRepository extends JpaRepository<Funcion, Long> {
    // Ya incluye count() automáticamente
}
