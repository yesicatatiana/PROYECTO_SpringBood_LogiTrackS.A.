package com.s1.proyecto_spring.service;

import com.s1.proyecto_spring.dto.request.BodegaRequestDTO;
import com.s1.proyecto_spring.dto.response.BodegaResponseDTO;
import java.util.List;

public interface BodegaService {
    BodegaResponseDTO guardar(BodegaRequestDTO dto);
    BodegaResponseDTO actualizar(BodegaRequestDTO dto, Long id);
    void eliminar(Long id);
    BodegaResponseDTO buscarPorId(Long id);
    List<BodegaResponseDTO> buscarTodos();
    BodegaResponseDTO activarDesactivar(Long id, Boolean activo);
}