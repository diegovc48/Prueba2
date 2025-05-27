package com.cine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    // ========== ADMIN ==========
    @GetMapping("/admin")
    public String loginAdminForm() {
        return "admin/login";
    }

    @PostMapping("/admin")
    public String loginAdmin(@RequestParam String correo,
                             @RequestParam String password,
                             Model model) {
        if (correo.equals("admin@cine.com") && password.equals("admin123")) {
            return "redirect:/admin/inicio";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "admin/login";
        }
    }
   

    // ========== TAQUILLERO ==========
    @GetMapping("/taquillero")
    public String loginTaquilleroForm() {
        return "taquillero/login";
    }

    @PostMapping("/taquillero")
    public String loginTaquillero(@RequestParam String correo,
                                  @RequestParam String password,
                                  Model model) {
        if (correo.equals("taquilla@cine.com") && password.equals("taquilla123")) {
            return "redirect:/taquillero/inicio";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "taquillero/login";
        }
    }

    // ========== SUPERVISOR ==========
    @GetMapping("/supervisor")
    public String loginSupervisorForm() {
        return "supervisor/login";
    }

    @PostMapping("/supervisor")
    public String loginSupervisor(@RequestParam String correo,
                                  @RequestParam String password,
                                  Model model) {
        if (correo.equals("supervisor@cine.com") && password.equals("supervisor123")) {
            return "redirect:/supervisor/inicio";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "supervisor/login";
        }
    }

    // ========== ENCARGADO DE CARTELERA ==========
    @GetMapping("/cartelera")
    public String loginCarteleraForm() {
        return "cartelera/login";
    }

    @PostMapping("/cartelera")
    public String loginCartelera(@RequestParam String correo,
                                 @RequestParam String password,
                                 Model model) {
        if (correo.equals("cartelera@cine.com") && password.equals("cartelera123")) {
            return "redirect:/cartelera/inicio";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "cartelera/login";
        }
    }
}
