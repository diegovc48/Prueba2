package com.cine.service;

import com.cine.model.Rol;
import com.cine.model.Usuario;
import com.cine.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario loginCliente(String correo, String password) {
        return usuarioRepository.findByCorreoAndPassword(correo, password)
                .filter(u -> u.getRol() == Rol.CLIENTE)
                .orElse(null);
    }


    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> login(String correo, String password) {
        return usuarioRepository.findByCorreoAndPassword(correo, password);
    }
}
