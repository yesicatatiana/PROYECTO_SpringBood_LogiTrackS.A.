package com.s1.proyecto_spring.dto.request;

public record InventarioRequestDTO(
        Long id,
        Integer stock,
        Long bodegaId,
        Long productoId
) {
}
