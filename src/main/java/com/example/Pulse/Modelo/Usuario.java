package com.example.Pulse.Modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;

    @Column(name = "contrasena")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role rol;

    public enum Role {
        USER, ADMIN
    }
}