package com.s1.proyecto_spring.dto.response;

import java.time.LocalDateTime;

public record AudioriaResponseDTO(
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
