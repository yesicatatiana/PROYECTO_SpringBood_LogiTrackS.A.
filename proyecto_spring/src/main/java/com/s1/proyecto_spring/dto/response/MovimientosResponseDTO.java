package com.s1.proyecto_spring.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record MovimientoResponseDTO(
        Long id,
        String tipo,
        LocalDateTime fecha,
        String estado,
        UsuarioResponseDTO usuario,
        BodegaResponseDTO bodegaOrigen,
        BodegaResponseDTO bodegaDestino,
        List<DetalleMovimientoResponseDTO> detalles
) {
}
