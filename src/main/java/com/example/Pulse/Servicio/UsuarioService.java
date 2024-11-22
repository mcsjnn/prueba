package com.example.Pulse.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pulse.Modelo.Usuario;
import com.example.Pulse.Repositorio.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario obtenerUsuarioPorEmail(String email) {
        // Se usa orElseThrow para lanzar una excepción si no se encuentra el usuario
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
