package com.example.Pulse.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pulse.Modelo.Pedido;
import com.example.Pulse.Modelo.Producto;
import com.example.Pulse.Modelo.Usuario;
import com.example.Pulse.Repositorio.PedidoRepository;
import com.example.Pulse.Repositorio.ProductoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public Pedido crearPedido(Usuario usuario, Long productoId, int cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setProducto(producto);
        pedido.setCantidad(cantidad);
        return pedidoRepository.save(pedido);
    }

}
