package com.cine.controller;

import com.cine.model.Funcion;
import com.cine.repository.EntradaRepository;
import com.cine.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @GetMapping("/inicio")
    public String verFunciones(Model model) {
        List<Funcion> funciones = funcionRepository.findAll();
        Map<Long, Long> entradasVendidas = new HashMap<>();

        for (Funcion f : funciones) {
            long cantidad = entradaRepository.countByFuncionIdAndEstado(f.getId(), "VENDIDA");
            entradasVendidas.put(f.getId(), cantidad);
        }

        model.addAttribute("funciones", funciones);
        model.addAttribute("ocupacion", entradasVendidas);
        return "supervisor/inicio";
    }
}
