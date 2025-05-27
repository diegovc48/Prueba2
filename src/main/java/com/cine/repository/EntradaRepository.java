package com.cine.repository;

import com.cine.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {

    // Para taquillero: buscar entradas reservadas
    List<Entrada> findByFuncionIdAndEstado(Long funcionId, String estado);

    // Para supervisor: cuántas vendidas por función
    long countByFuncionIdAndEstado(Long funcionId, String estado);

    // Para cliente: historial
    List<Entrada> findByUsuarioId(Long usuarioId);

    // Para ver entradas por función
    List<Entrada> findByFuncionId(Long funcionId);

    // Contador general por estado
    long countByEstado(String estado);
}
