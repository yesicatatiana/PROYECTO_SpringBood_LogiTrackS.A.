package com.s1.proyecto_spring.dto.response;

import com.s1.proyecto_spring.model.Auditoria;

import java.time.LocalDateTime;

public record AuditoriaResponseDTO(
        Long id,
        String operacion,
        String entidad,
        String valoresAnteriores,
        String valoresNuevos,
        String origen,
        LocalDateTime fechaHora,
        UsuarioResponseDTO usuario
) {
}
