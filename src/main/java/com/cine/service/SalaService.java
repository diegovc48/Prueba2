package com.cine.service;

import com.cine.model.Sala;
import com.cine.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    public Sala guardarSala(Sala sala) {
        return salaRepository.save(sala);
    }

    public Optional<Sala> obtenerPorId(Long id) {
        return salaRepository.findById(id);
    }

    public void eliminarSala(Long id) {
        salaRepository.deleteById(id);
    }
}
