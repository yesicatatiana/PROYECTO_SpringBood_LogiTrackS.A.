package com.s1.proyecto_spring.dto.request;

// BodegaRequestDTO
public record BodegaRequestDTO(
        String nombre,
        String ubicacion,
        Integer capacidad,
        Boolean activo,
        Long encargadoId
) {
}