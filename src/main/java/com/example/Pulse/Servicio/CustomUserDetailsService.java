package com.example.Pulse.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Pulse.Modelo.Usuario;
import com.example.Pulse.Repositorio.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Comparar contraseñas codificadas
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword()) // La contraseña debe estar codificada
                .roles(usuario.getRol().name())
                .build();
    }
}
