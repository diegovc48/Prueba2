package com.cine.controller;

import com.cine.repository.EntradaRepository;
import com.cine.repository.FuncionRepository;
import com.cine.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/reportes")
public class ReporteController {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @GetMapping
    public String verReportes(Model model) {
        long totalFunciones = funcionRepository.count();
        long entradasVendidas = entradaRepository.countByEstado("VENDIDA");
        long totalSalas = salaRepository.count();

        model.addAttribute("totalFunciones", totalFunciones);
        model.addAttribute("entradasVendidas", entradasVendidas);
        model.addAttribute("totalSalas", totalSalas);

        return "admin/reportes/estadisticas";
    }
}
