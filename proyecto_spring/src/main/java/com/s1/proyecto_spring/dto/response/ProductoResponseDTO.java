package com.s1.proyecto_spring.dto.response;

import java.math.BigDecimal;

public record ProductoResponseDTO(
        Long id,
        String nombre,
        String descripcion,
        BigDecimal precio,
        Integer stock,
        String categoria
) {
}
