package com.cine.controller;

import com.cine.model.Pelicula;
import com.cine.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public String listarPeliculas(Model model) {
        model.addAttribute("peliculas", peliculaService.listarPeliculas());
        return "admin/peliculas/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("pelicula", new Pelicula());
        return "admin/peliculas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("pelicula") Pelicula pelicula) {
        peliculaService.guardarPelicula(pelicula);
        return "redirect:/admin/peliculas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Pelicula pelicula = peliculaService.obtenerPeliculaPorId(id);
        if (pelicula != null) {
            model.addAttribute("pelicula", pelicula);
            return "admin/peliculas/formulario";
        }
        return "redirect:/admin/peliculas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        peliculaService.eliminarPelicula(id);
        return "redirect:/admin/peliculas";
    }
}
