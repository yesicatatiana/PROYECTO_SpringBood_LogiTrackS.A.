
package com.s1.proyecto_spring.service;

import com.s1.proyecto_spring.dto.response.RolResponseDTO;
import java.util.List;

    public interface RolService {
        RolResponseDTO buscarPorId(Long id);
        List<RolResponseDTO> buscarTodos();
    }

