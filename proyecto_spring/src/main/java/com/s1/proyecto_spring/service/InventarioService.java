package com.s1.proyecto_spring.service;

import com.s1.proyecto_spring.dto.request.InventarioRequestDTO;
import com.s1.proyecto_spring.dto.response.InventarioResponseDTO;
import java.util.List;

public interface InventarioService {
    InventarioResponseDTO guardar(InventarioRequestDTO dto);
    InventarioResponseDTO actualizar(InventarioRequestDTO dto, Long id);
    void eliminar(Long id);
    InventarioResponseDTO buscarPorId(Long id);
    List<InventarioResponseDTO> buscarTodos();
}