package com.s1.proyecto_spring.dto.request;

import com.s1.proyecto_spring.model.Auditoria;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AuditoriaRequestDTO(
        Auditoria.TipoOperacion operacion,
        String entidad,
        String valoresAnteriores,
        String valoresNuevos,
        String origen,
        LocalDateTime fechaHora,
        Long usuarioId

) {
}
