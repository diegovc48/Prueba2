package com.cine.controller;

import com.cine.model.Rol;
import com.cine.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    // ✅ Mostrar formulario de login para ADMIN
    @GetMapping("/login")
    public String mostrarLoginAdmin() {
        return "admin/login"; // ← apunta a templates/admin/login.html
    }

    // ✅ Procesar login ADMIN con datos quemados
    @PostMapping("/login")
    public String procesarLoginAdmin(@RequestParam String correo,
                                     @RequestParam String password,
                                     Model model) {

        // Datos quemados para el administrador
        String adminCorreo = "admin@cine.com";
        String adminPassword = "admin123";

        if (correo.equals(adminCorreo) && password.equals(adminPassword)) {
            model.addAttribute("nombre", "Administrador");
            return "admin/inicio"; // ← apunta a templates/admin/inicio.html
        } else {
            model.addAttribute("error", "Credenciales incorrectas.");
            return "admin/login";
        }
    }
    
}
