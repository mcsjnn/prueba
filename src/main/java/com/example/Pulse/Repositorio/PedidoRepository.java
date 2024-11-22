package com.example.Pulse.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Pulse.Modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
