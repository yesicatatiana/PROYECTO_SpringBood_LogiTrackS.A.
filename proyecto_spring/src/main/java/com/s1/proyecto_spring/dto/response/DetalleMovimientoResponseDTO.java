package com.s1.proyecto_spring.dto.response;

import java.math.BigDecimal;

public record DetalleMovimientoResponseDTO(
        Long id,
        Integer cantidad,
        BigDecimal precioUnitario,
        ProductoResponseDTO producto
) {
}
