package com.example.Pulse.Controlador;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.Pulse.Modelo.Usuario;
import com.example.Pulse.Servicio.PedidoService;
import com.example.Pulse.Servicio.UsuarioService;

@Controller
public class PedidoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/pedidos/crear")
    public String crearPedido(@RequestParam Long productoId, @RequestParam int cantidad, Principal principal) {
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(principal.getName()); // Obtener el usuario logueado
        pedidoService.crearPedido(usuario, productoId, cantidad);
        return "redirect:/pedidos";
    }
}
