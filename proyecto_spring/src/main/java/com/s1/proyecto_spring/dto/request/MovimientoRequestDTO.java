package com.s1.proyecto_spring.dto.request;

import com.s1.proyecto_spring.model.Movimientos;

import java.time.LocalDate;

public record MovimientoRequestDTO(
        Long id,
        String tipo,
        LocalDate fecha,
        String estado,
        Long usuarioId,
        Long bodegaOrigenId,
        Long bodegaDestinoId
) {
}
