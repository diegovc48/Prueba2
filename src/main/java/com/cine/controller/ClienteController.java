package com.cine.controller;

import com.cine.model.Rol;
import com.cine.model.Usuario;
import com.cine.repository.FuncionRepository;
import com.cine.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FuncionRepository funcionRepository;

    @GetMapping("/registro")
    public String mostrarRegistroCliente() {
        return "cliente/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistroCliente(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String correo,
            @RequestParam String password,
            @RequestParam String cedula,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento,
            Model model) {

        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        if (edad < 18) {
            model.addAttribute("error", "Debes ser mayor de edad para registrarte.");
            return "cliente/registro";
        }

        Usuario nuevoCliente = new Usuario();
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setApellido(apellido);
        nuevoCliente.setCorreo(correo);
        nuevoCliente.setPassword(password);
        nuevoCliente.setCedula(cedula);
        nuevoCliente.setFechaNacimiento(fechaNacimiento);
        nuevoCliente.setRol(Rol.CLIENTE);

        usuarioService.guardarUsuario(nuevoCliente);
        return "redirect:/clientes/login";
    }

    @GetMapping("/login")
    public String mostrarLoginCliente() {
        return "cliente/login";
    }

    @PostMapping("/login")
    public String procesarLoginCliente(
            @RequestParam String correo,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        Usuario usuario = usuarioService.loginCliente(correo, password);

        if (usuario != null && usuario.getRol() == Rol.CLIENTE) {
            session.setAttribute("usuarioCliente", usuario); // Guardar en sesión
            model.addAttribute("nombre", usuario.getNombre());
            return "cliente/inicio";
        }

        model.addAttribute("error", "Correo o contraseña incorrectos.");
        return "cliente/login";
    }
}
