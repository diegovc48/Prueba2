package com.cine.controller;

import com.cine.model.Entrada;
import com.cine.model.Funcion;
import com.cine.model.Usuario;
import com.cine.repository.EntradaRepository;
import com.cine.repository.FuncionRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cliente/cartelera")
public class CarteleraClienteController {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    // Mostrar cartelera
    @GetMapping
    public String verCartelera(Model model, HttpSession session) {
        model.addAttribute("funciones", funcionRepository.findAll());

        // Mostrar mensaje si existe en sesión
        String mensaje = (String) session.getAttribute("mensajeReserva");
        if (mensaje != null) {
            model.addAttribute("mensajeReserva", mensaje);
            session.removeAttribute("mensajeReserva");
        }

        return "cliente/cartelera";
    }

    // Ver asientos disponibles para una función
    @GetMapping("/reservar/{idFuncion}")
    public String mostrarAsientos(@PathVariable Long idFuncion, Model model) {
        Funcion funcion = funcionRepository.findById(idFuncion).orElse(null);
        if (funcion != null) {
            List<Entrada> disponibles = entradaRepository.findByFuncionIdAndEstado(idFuncion, "DISPONIBLE");
            model.addAttribute("funcion", funcion);
            model.addAttribute("entradas", disponibles);
            return "cliente/reserva";
        }
        return "redirect:/cliente/cartelera";
    }

    // Reservar asiento (CORREGIDO)
    @PostMapping("/reservar/{idEntrada}")
    public String reservarEntrada(@PathVariable Long idEntrada, HttpSession session, Model model) {
        Entrada entrada = entradaRepository.findById(idEntrada).orElse(null);
        Usuario cliente = (Usuario) session.getAttribute("usuarioCliente"); // obtener cliente desde sesión

        if (entrada != null && cliente != null && "DISPONIBLE".equals(entrada.getEstado())) {
            entrada.setEstado("RESERVADA");
            entrada.setUsuario(cliente); // asociar usuario real
            entradaRepository.save(entrada);
            session.setAttribute("mensajeReserva", "✅ Asiento reservado exitosamente.");
        } else {
            session.setAttribute("mensajeReserva", "⚠️ No se pudo reservar el asiento.");
        }

        return "redirect:/cliente/cartelera";
    }
}
