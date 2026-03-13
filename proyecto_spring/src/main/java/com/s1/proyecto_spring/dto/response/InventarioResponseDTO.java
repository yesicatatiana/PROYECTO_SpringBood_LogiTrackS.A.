package com.s1.proyecto_spring.dto.response;

public record InventarioResponseDTO(
        Long id,
        Integer stock,
        BodegaResponseDTO bodega,
        ProductoResponseDTO producto
) {
}
