package com.s1.proyecto_spring.service;

import com.s1.proyecto_spring.dto.request.UsuarioRequestDTO;
import com.s1.proyecto_spring.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {

        UsuarioResponseDTO guardar(UsuarioRequestDTO dto);
        UsuarioResponseDTO actualizar(UsuarioRequestDTO dto, Long id);
        void eliminar(Long id);
        UsuarioResponseDTO buscarPorId(Long id);
        List<UsuarioResponseDTO> buscarTodos();
        UsuarioResponseDTO activarDesactivar(Long id, Boolean activo);

}
