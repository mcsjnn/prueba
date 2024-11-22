package com.example.Pulse.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Pulse.Modelo.Pedido;
import com.example.Pulse.Repositorio.PedidoRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Ruta para ver el formulario de pedido
    @GetMapping("/nuevo")
    public String nuevoPedido(Model model) {
        model.addAttribute("pedido", new Pedido());
        return "formulario_pedido";
    }

    // Ruta para confirmar el pedido
    @PostMapping("/nuevo")
    public String crearPedido(@ModelAttribute Pedido pedido) {
        // Guardar el pedido en la base de datos
        pedidoRepository.save(pedido);
        return "redirect:/pedido/confirmado";
    }

    // Ruta para ver la confirmación del pedido
    @GetMapping("/confirmado")
    public String pedidoConfirmado() {
        return "pedido_confirmado";
    }
}
