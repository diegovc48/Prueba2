package com.cine.controller;

import com.cine.model.Entrada;
import com.cine.model.Funcion;
import com.cine.model.Pelicula;
import com.cine.model.Sala;
import com.cine.repository.EntradaRepository;
import com.cine.repository.FuncionRepository;
import com.cine.repository.PeliculaRepository;
import com.cine.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cartelera")
public class CarteleraController {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    // ==================== FUNCIONES ====================

    @GetMapping("/inicio")
    public String verFunciones(Model model) {
        model.addAttribute("funciones", funcionRepository.findAll());
        return "cartelera/inicio";
    }

    @GetMapping("/nueva")
    public String nuevaFuncion(Model model) {
        model.addAttribute("funcion", new Funcion());
        model.addAttribute("peliculas", peliculaRepository.findAll());
        model.addAttribute("salas", salaRepository.findAll());
        return "cartelera/funciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardarFuncion(@ModelAttribute Funcion funcion) {
        funcionRepository.save(funcion);

        // Crear automáticamente 30 asientos
        for (int i = 1; i <= 30; i++) {
            Entrada entrada = new Entrada();
            entrada.setAsiento("A" + i);
            entrada.setEstado("DISPONIBLE");
            entrada.setFuncion(funcion);
            entradaRepository.save(entrada);
        }

        return "redirect:/cartelera/inicio";
    }

    @GetMapping("/editar/{id}")
    public String editarFuncion(@PathVariable Long id, Model model) {
        Funcion funcion = funcionRepository.findById(id).orElse(null);
        if (funcion != null) {
            model.addAttribute("funcion", funcion);
            model.addAttribute("peliculas", peliculaRepository.findAll());
            model.addAttribute("salas", salaRepository.findAll());
            return "cartelera/funciones/formulario";
        }
        return "redirect:/cartelera/inicio";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFuncion(@PathVariable Long id) {
        funcionRepository.deleteById(id);
        return "redirect:/cartelera/inicio";
    }

    // ==================== PELÍCULAS ====================

    @GetMapping("/peliculas")
    public String verPeliculas(Model model) {
        model.addAttribute("peliculas", peliculaRepository.findAll());
        return "cartelera/peliculas/lista";
    }

    @GetMapping("/peliculas/nueva")
    public String nuevaPelicula(Model model) {
        model.addAttribute("pelicula", new Pelicula());
        return "cartelera/peliculas/formulario";
    }

    @PostMapping("/peliculas/guardar")
    public String guardarPelicula(@ModelAttribute Pelicula pelicula) {
        peliculaRepository.save(pelicula);
        return "redirect:/cartelera/peliculas";
    }

    @GetMapping("/peliculas/editar/{id}")
    public String editarPelicula(@PathVariable Long id, Model model) {
        Pelicula pelicula = peliculaRepository.findById(id).orElse(null);
        if (pelicula != null) {
            model.addAttribute("pelicula", pelicula);
            return "cartelera/peliculas/formulario";
        }
        return "redirect:/cartelera/peliculas";
    }

    @GetMapping("/peliculas/eliminar/{id}")
    public String eliminarPelicula(@PathVariable Long id) {
        peliculaRepository.deleteById(id);
        return "redirect:/cartelera/peliculas";
    }
}
