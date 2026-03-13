package com.s1.proyecto_spring.dto.response;

import com.s1.proyecto_spring.model.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record RolResponseDTO(
        Long id,

        String rol
) {
}
