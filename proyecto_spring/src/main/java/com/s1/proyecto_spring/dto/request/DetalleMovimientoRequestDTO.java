package com.s1.proyecto_spring.dto.request;

import java.math.BigDecimal;

public record DetalleMovimientoRequestDTO(
        Integer cantidad,
        BigDecimal precioUnitario,
        Long movimientoId,
        Long productoId
) {
}
