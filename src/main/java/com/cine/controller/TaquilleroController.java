package com.cine.controller;

import com.cine.model.Entrada;
import com.cine.model.Funcion;
import com.cine.repository.EntradaRepository;
import com.cine.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/taquillero")
public class TaquilleroController {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @GetMapping("/inicio")
    public String verFunciones(Model model) {
        List<Funcion> funciones = funcionRepository.findAll();

        // Si no hay funciones o no hay entradas reservadas, no se muestra nada útil
        model.addAttribute("funciones", funciones);
        return "taquillero/inicio";
    }

    @GetMapping("/venta/{idFuncion}")
    public String verEntradasReservadas(@PathVariable Long idFuncion, Model model) {
        Funcion funcion = funcionRepository.findById(idFuncion).orElse(null);
        if (funcion != null) {
            List<Entrada> reservadas = entradaRepository.findByFuncionIdAndEstado(idFuncion, "RESERVADA");
            model.addAttribute("funcion", funcion);
            model.addAttribute("entradas", reservadas);
            return "taquillero/venta";
        }
        return "redirect:/taquillero/inicio";
    }

    @PostMapping("/vender/{idEntrada}")
    public String venderEntrada(@PathVariable Long idEntrada) {
        Entrada entrada = entradaRepository.findById(idEntrada).orElse(null);
        if (entrada != null && "RESERVADA".equals(entrada.getEstado())) {
            entrada.setEstado("VENDIDA");
            entradaRepository.save(entrada);
        }
        return "redirect:/taquillero/inicio";
    }
}
