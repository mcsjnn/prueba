package com.example.Pulse.Controlador;

import java.util.List;

public class PedidoRequest {
    private String cliente;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    private List<Long> productos;

    public List<Long> getProductos() {
        return productos;
    }

    public void setProductos(List<Long> productos) {
        this.productos = productos;
    }

    private List<Integer> cantidades;

    public List<Integer> getCantidades() {
        return cantidades;
    }

    public void setCantidades(List<Integer> cantidades) {
        this.cantidades = cantidades;
    }
}
