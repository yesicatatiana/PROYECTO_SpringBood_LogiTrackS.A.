package com.s1.proyecto_spring.dto.response;

public record BodegaResponseDTO(
        Long id,
        String nombre,
        String ubicacion,
        Integer capacidad,
        Boolean activo,
        UsuarioResponseDTO encargado
) {
}
