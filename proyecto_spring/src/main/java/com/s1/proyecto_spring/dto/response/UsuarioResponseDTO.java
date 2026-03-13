package com.s1.proyecto_spring.dto.response;

public record UsuarioResponseDTO(
        Long id,
        String nombre,
        String apellido,
        String email,
        Boolean activo,
        RolResponseDTO rol
) {
}
