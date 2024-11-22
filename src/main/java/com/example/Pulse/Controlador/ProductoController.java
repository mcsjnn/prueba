package com.example.Pulse.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Pulse.Servicio.ProductoService;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public String mostrarMenuProductos(Model model) {
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "menu_productos"; // La vista que muestra el menú de productos
    }
}
