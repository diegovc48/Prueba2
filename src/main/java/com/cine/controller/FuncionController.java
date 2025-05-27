package com.cine.controller;

import com.cine.model.Funcion;
import com.cine.model.Pelicula;
import com.cine.model.Sala;
import com.cine.service.FuncionService;
import com.cine.service.PeliculaService;
import com.cine.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/funciones")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private SalaService salaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("funciones", funcionService.listarFunciones());
        return "admin/funciones/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("funcion", new Funcion());
        model.addAttribute("peliculas", peliculaService.listarPeliculas());
        model.addAttribute("salas", salaService.listarSalas());
        return "admin/funciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("funcion") Funcion funcion) {
        funcionService.guardar(funcion);
        return "redirect:/admin/funciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Funcion funcion = funcionService.obtenerPorId(id).orElse(null);
        if (funcion != null) {
            model.addAttribute("funcion", funcion);
            model.addAttribute("peliculas", peliculaService.listarPeliculas());
            model.addAttribute("salas", salaService.listarSalas());
            return "admin/funciones/formulario";
        }
        return "redirect:/admin/funciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        funcionService.eliminar(id);
        return "redirect:/admin/funciones";
    }
}
