package com.cine.controller;

import com.cine.model.Entrada;
import com.cine.model.Usuario;
import com.cine.repository.EntradaRepository;
import com.cine.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 🔒 Reservar una entrada
    @PostMapping("/cliente/{idEntrada}")
    public String reservarEntrada(@PathVariable Long idEntrada) {
        Entrada entrada = entradaRepository.findById(idEntrada).orElse(null);

        if (entrada != null && entrada.getEstado().equals("DISPONIBLE")) {
            entrada.setEstado("RESERVADA");

            // 🔥 Usuario simulado (debería venir de sesión en el futuro)
            Usuario cliente = usuarioRepository.findByCorreo("cliente@cine.com").orElse(null);
            entrada.setUsuario(cliente);

            entradaRepository.save(entrada);
        }

        return "redirect:/cliente/cartelera";
    }
}
