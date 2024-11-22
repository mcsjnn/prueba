package com.example.Pulse.Configuración;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authConfig -> {
                    authConfig
                            // Permitir acceso a rutas abiertas primero
                            .requestMatchers("/", "/inicio", "/menu_productos", "/registro", "/css/**", "/js/**")
                            .permitAll()
                            // Luego aplicar las restricciones de roles para áreas protegidas
                            .requestMatchers("/admin/**").hasRole("ADMIN") // Solo admins
                            .requestMatchers("/perfil/**").authenticated() // Usuarios autenticados
                            // Finalmente, requerir autenticación para cualquier otra ruta no definida
                            // anteriormente
                            .anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        /* Maneja la redireccion despues de iniciar sesion */
                        .successHandler((request, response, authentication) -> {
                            if (authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                                response.sendRedirect("/admin/panel");
                            } else {
                                response.sendRedirect("/perfil");
                            }
                        })
                        .failureUrl("/login?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/inicio")
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
