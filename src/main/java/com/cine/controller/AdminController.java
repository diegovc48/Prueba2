package com.cine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/inicio")
    public String vistaInicioAdmin() {
        return "admin/inicio";
    }
}
