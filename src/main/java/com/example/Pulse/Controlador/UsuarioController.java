package com.example.Pulse.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Pulse.Modelo.Usuario;
import com.example.Pulse.Repositorio.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Ruta para la página de inicio
    @GetMapping({ "/", "/inicio" })
    public String inicio() {
        return "inicio";
    }

    // Ruta para mostrar el perfil del usuario autenticado
    @GetMapping("/perfil")
    public String perfil(Model model, Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "perfil";
    }

    // Ruta para mostrar el formulario de registro
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Ruta para registrar un nuevo usuario
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        // Verificar si el correo electrónico ya está registrado
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            model.addAttribute("error", "El correo electrónico ya está registrado.");
            return "registro"; // Regresar al formulario con un error
        }

        // Codificar la contraseña con el PasswordEncoder
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword); // Asigna la contraseña codificada

        // Asignar el rol por defecto si es necesario
        if (usuario.getRol() == null) {
            usuario.setRol(Usuario.Role.USER); // Si no se especifica, asignar el rol de usuario por defecto
        }

        // Guardar el usuario
        usuarioRepository.save(usuario);

        // Redirigir a la página de perfil o cualquier otra página que desees
        return "redirect:/perfil";
    }

    // Ruta para el login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Ruta para editar el perfil
    @GetMapping("/perfil/editar")
    public String editarPerfil(Model model, Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "editar-perfil";
    }

    // Ruta para actualizar el perfil
    @PostMapping("/perfil/editar")
    public String actualizarPerfil(@ModelAttribute Usuario usuarioActualizado, Authentication authentication) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setApellido(usuarioActualizado.getApellido());
        usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
        usuarioExistente.setDireccion(usuarioActualizado.getDireccion());

        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        }

        usuarioRepository.save(usuarioExistente);
        return "redirect:/perfil?actualizado=true";
    }
}
