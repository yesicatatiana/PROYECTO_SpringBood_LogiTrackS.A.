package com.s1.proyecto_spring.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetalleMovimientoResponseDTO(
        Long id,
        Integer cantidad,
        BigDecimal precioUnitario,
        ProductoResponseDTO producto
) {
}
