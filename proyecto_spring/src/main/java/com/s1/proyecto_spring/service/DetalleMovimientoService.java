
package com.s1.proyecto_spring.service;

import com.s1.proyecto_spring.dto.request.DetalleMovimientoRequestDTO;
import com.s1.proyecto_spring.dto.response.DetalleMovimientoResponseDTO;
import java.util.List;

public interface DetalleMovimientoService {
    DetalleMovimientoResponseDTO guardar(DetalleMovimientoRequestDTO dto);
    DetalleMovimientoResponseDTO actualizar(DetalleMovimientoRequestDTO dto, Long id);
    void eliminar(Long id);
    DetalleMovimientoResponseDTO buscarPorId(Long id);
    List<DetalleMovimientoResponseDTO> buscarTodos();
    List<DetalleMovimientoResponseDTO> buscarPorMovimiento(Long movimientoId);
}