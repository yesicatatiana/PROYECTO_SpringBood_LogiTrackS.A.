package com.s1.proyecto_spring.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsuarioRequestDTO(


        Long id,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 100)
        String apellido,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Email inválido")
        @Size(max = 150)
        String email,

        @NotBlank(message = "La password es obligatoria")
        @Size(min = 8, max = 255)
        String password,

        @NotEmpty(message = "Debe asignar al menos un rol")
        Long rolesId

) {
}
