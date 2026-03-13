package com.s1.proyecto_spring.dto.request;

public record BodegaRequestDTO(
        Long id,
        String nombre,
        String ubicacion,
        Integer capacidad,
        Boolean activo,
        Long encagadoId
) {
}
