package com.example.Pulse.Configuración;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Pulse.Modelo.Usuario;
import com.example.Pulse.Repositorio.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Crear admin si no existe
        if (!usuarioRepository.findByEmail("admin@ejemplo1.com").isPresent()) {
            Usuario admin = new Usuario();
            admin.setNombre("Admin");
            admin.setApellido("Principal");
            admin.setEmail("admin@ejemplo1.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(Usuario.Role.ADMIN);
            usuarioRepository.save(admin);
        }
    }
}