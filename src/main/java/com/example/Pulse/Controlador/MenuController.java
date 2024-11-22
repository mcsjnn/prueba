package com.example.Pulse.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.Pulse.Modelo.Producto;
import com.example.Pulse.Servicio.ProductoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MenuController {

    @Autowired
    private ProductoService productoService; // Asegúrate de tener un servicio para manejar los productos

    @GetMapping("/menu")
    public String mostrarMenu(Model model) {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        return "menu";
    }
}
