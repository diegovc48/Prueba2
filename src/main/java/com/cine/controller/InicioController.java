package com.cine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    // Esta ruta muestra la página principal al abrir localhost:8080/
    @GetMapping("/")
    public String mostrarInicio() {
        return "inicio"; // Busca templates/inicio.html
    }
}
