package com.cine.controller;

import com.cine.model.Sala;
import com.cine.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("salas", salaService.listarSalas());
        return "admin/salas/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("sala", new Sala());
        return "admin/salas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Sala sala) {
        salaService.guardarSala(sala);
        return "redirect:/admin/salas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Sala sala = salaService.obtenerPorId(id).orElse(null);
        if (sala != null) {
            model.addAttribute("sala", sala);
            return "admin/salas/formulario";
        }
        return "redirect:/admin/salas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        salaService.eliminarSala(id);
        return "redirect:/admin/salas";
    }
}
