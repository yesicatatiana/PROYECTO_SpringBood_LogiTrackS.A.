package com.s1.proyecto_spring.dto.request;

import java.math.BigDecimal;

public record DetalleMovimientoRequestDTO(
        Long id,
        Integer cantidad,
        BigDecimal precioUnitario,
        Long movimientoId,
        Long productoId
) {
}
