package com.cine.controller;

import com.cine.model.Entrada;
import com.cine.model.Funcion;
import com.cine.model.Usuario;
import com.cine.repository.EntradaRepository;
import com.cine.repository.FuncionRepository;
import com.cine.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteCarteleraController {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✅ Mostrar cartelera
    @GetMapping("/cartelera")
    public String verCartelera(Model model) {
        model.addAttribute("funciones", funcionRepository.findAll());
        return "cliente/cartelera";
    }

    // ✅ Mostrar asientos disponibles de una función
    @GetMapping("/reservar/{idFuncion}")
    public String reservar(@PathVariable Long idFuncion, Model model) {
        Funcion funcion = funcionRepository.findById(idFuncion).orElse(null);
        List<Entrada> entradas = entradaRepository.findByFuncionIdAndEstado(idFuncion, "DISPONIBLE");

        model.addAttribute("funcion", funcion);
        model.addAttribute("entradas", entradas);
        return "cliente/reservar";
    }

    // ✅ Reservar asiento
    @PostMapping("/reservar/{idEntrada}")
    public String procesarReserva(@PathVariable Long idEntrada) {
        Entrada entrada = entradaRepository.findById(idEntrada).orElse(null);

        if (entrada != null) {
            entrada.setEstado("RESERVADA");

            // 🔒 Aquí deberías obtener el cliente logueado
            // Ahora lo asignamos a un usuario quemado para prueba
            Usuario cliente = usuarioRepository.findByCorreo("cliente@cine.com").orElse(null);

            entrada.setUsuario(cliente);

            entradaRepository.save(entrada);
        }

        return "redirect:/clientes/cartelera";
    }
}
