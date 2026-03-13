package com.s1.proyecto_spring.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AuditoriaRequestDTO(
        Long id,
        String operacion,
        String entidad,
        String valoresAnteriores,
        String valoresNuevos,
        String origen,
        LocalDateTime fecha,
        Long usuarioId

) {
}
