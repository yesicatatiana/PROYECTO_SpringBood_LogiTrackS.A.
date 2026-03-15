package com.s1.proyecto_spring.dto.request;

import java.math.BigDecimal;

public record ProductoRequestDTO(
        String nombre,
        String descripcion,
        BigDecimal precio,
        Integer stock,
        String categoria
) {
}
