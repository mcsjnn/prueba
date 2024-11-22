package com.example.Pulse.Modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios") // Esto mapea la clase a la tabla 'usuarios' en la base de datos
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Se mapea con la columna 'id'

    private String nombre; // Mapea con la columna 'nombre'
    private String apellido; // Mapea con la columna 'apellido'

    @Column(name = "email") // Asegura que el nombre de la columna en la base de datos sea 'email'
    private String email; // Mapea con la columna 'email'

    private String telefono; // Mapea con la columna 'telefono'

    private String direccion; // Mapea con la columna 'direccion'

    @Column(name = "password") // Mapea la columna 'contrasena' en la base de datos a 'password'
    private String password; // Mapea con la columna 'contraseña'

    @Enumerated(EnumType.STRING)
    private Role rol; // Mapea con la columna 'rol'

    // Definición de los roles
    public enum Role {
        USER, ADMIN
    }
}
