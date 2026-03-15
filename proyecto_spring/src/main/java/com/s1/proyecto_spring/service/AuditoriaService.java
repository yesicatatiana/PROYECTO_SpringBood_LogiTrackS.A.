package com.s1.proyecto_spring.service;

import com.s1.proyecto_spring.dto.request.AuditoriaRequestDTO;
import com.s1.proyecto_spring.dto.response.AuditoriaResponseDTO;
import com.s1.proyecto_spring.model.Auditoria;
import java.util.List;

public interface AuditoriaService {
    AuditoriaResponseDTO guardar(AuditoriaRequestDTO dto);
    AuditoriaResponseDTO buscarPorId(Long id);
    List<AuditoriaResponseDTO> buscarTodos();
    List<AuditoriaResponseDTO> buscarPorUsuario(Long usuarioId);
    List<AuditoriaResponseDTO> buscarPorOperacion(Auditoria.TipoOperacion operacion);
    List<AuditoriaResponseDTO> buscarPorUsuarioYOperacion(Long usuarioId, Auditoria.TipoOperacion operacion);
}