package com.cine.service;

import com.cine.model.Pelicula;
import com.cine.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    // Guardar o actualizar una película
    public Pelicula guardarPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    // Obtener todas las películas
    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll();
    }

    // Buscar una película por ID
    public Pelicula obtenerPeliculaPorId(Long id) {
        Optional<Pelicula> optional = peliculaRepository.findById(id);
        return optional.orElse(null);
    }

    // Eliminar una película por ID
    public void eliminarPelicula(Long id) {
        peliculaRepository.deleteById(id);
    }
}
