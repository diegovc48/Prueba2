package com.cine.service;

import com.cine.model.Funcion;
import com.cine.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionService {

    @Autowired
    private FuncionRepository funcionRepository;

    public List<Funcion> listarFunciones() {
        return funcionRepository.findAll();
    }

    public Funcion guardar(Funcion funcion) {
        return funcionRepository.save(funcion);
    }

    public Optional<Funcion> obtenerPorId(Long id) {
        return funcionRepository.findById(id);
    }

    public void eliminar(Long id) {
        funcionRepository.deleteById(id);
    }
}
