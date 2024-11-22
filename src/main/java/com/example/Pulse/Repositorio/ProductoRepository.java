package com.example.Pulse.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Pulse.Modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
