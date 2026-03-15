package com.s1.proyecto_spring.dto.response;

import com.s1.proyecto_spring.model.Movimientos;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

public record MovimientosResponseDTO(
        Long id,
        Movimientos.TipoMovimiento tipo,
        LocalDateTime fecha,
        Movimientos.EstadoMovimiento estado,
        UsuarioResponseDTO usuario,
        BodegaResponseDTO bodegaOrigen,
        BodegaResponseDTO bodegaDestino,
        List<DetalleMovimientoResponseDTO> detalles
) {
}
