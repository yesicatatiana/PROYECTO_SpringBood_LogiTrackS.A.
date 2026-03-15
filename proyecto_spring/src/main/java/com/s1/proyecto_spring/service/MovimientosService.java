package com.s1.proyecto_spring.service;

import com.s1.proyecto_spring.dto.request.MovimientosRequestDTO;
import com.s1.proyecto_spring.dto.response.MovimientosResponseDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimientosService {
    MovimientosResponseDTO guardar(MovimientosRequestDTO dto);
    MovimientosResponseDTO actualizar(MovimientosRequestDTO dto, Long id);
    void eliminar(Long id);
    MovimientosResponseDTO buscarPorId(Long id);
    List<MovimientosResponseDTO> buscarTodos();
    List<MovimientosResponseDTO> buscarPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}