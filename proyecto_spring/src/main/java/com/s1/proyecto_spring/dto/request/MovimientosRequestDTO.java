package com.s1.proyecto_spring.dto.request;


import com.s1.proyecto_spring.model.Movimientos;

import java.time.LocalDateTime;

public record MovimientosRequestDTO(
        Movimientos.TipoMovimiento tipo,        // ← enum, no String
        LocalDateTime fecha,        // ← LocalDateTime, no LocalDate
        Movimientos.EstadoMovimiento estado,    // ← enum, no String
        Long usuarioId,
        Long bodegaOrigenId,
        Long bodegaDestinoId
) {
}
