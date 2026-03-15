package com.s1.proyecto_spring.service.impl;

import com.s1.proyecto_spring.dto.request.AuditoriaRequestDTO;
import com.s1.proyecto_spring.dto.response.AuditoriaResponseDTO;
import com.s1.proyecto_spring.dto.response.RolResponseDTO;
import com.s1.proyecto_spring.dto.response.UsuarioResponseDTO;
import com.s1.proyecto_spring.mapper.AuditoriaMapper;
import com.s1.proyecto_spring.mapper.RolMapper;
import com.s1.proyecto_spring.mapper.UsuarioMapper;
import com.s1.proyecto_spring.model.Auditoria;
import com.s1.proyecto_spring.model.Usuario;
import com.s1.proyecto_spring.repository.AuditoriaRepository;
import com.s1.proyecto_spring.repository.UsuarioRepository;
import com.s1.proyecto_spring.service.AuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final AuditoriaMapper auditoriaMapper;
    private final UsuarioMapper usuarioMapper;
    private final RolMapper rolMapper;

    // helper: Usuario → UsuarioResponseDTO
    private UsuarioResponseDTO toUsuarioDTO(Usuario usuario) {
        RolResponseDTO rolDTO = rolMapper.entidadADTO(usuario.getRol());
        return usuarioMapper.entidadADTO(usuario, rolDTO);
    }

    // helper: Auditoria → AuditoriaResponseDTO
    private AuditoriaResponseDTO toAuditoriaDTO(Auditoria auditoria) {
        return auditoriaMapper.entidadADTO(auditoria, toUsuarioDTO(auditoria.getUsuario()));
    }

    @Override
    public AuditoriaResponseDTO guardar(AuditoriaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.usuarioId()));

        Auditoria auditoria = auditoriaMapper.DTOAEntidad(dto, usuario);
        Auditoria guardada = auditoriaRepository.save(auditoria);
        return toAuditoriaDTO(guardada);
    }

    @Override
    public AuditoriaResponseDTO buscarPorId(Long id) {
        Auditoria auditoria = auditoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auditoria no encontrada con id: " + id));
        return toAuditoriaDTO(auditoria);
    }

    @Override
    public List<AuditoriaResponseDTO> buscarTodos() {
        return auditoriaRepository.findAll()
                .stream()
                .map(this::toAuditoriaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditoriaResponseDTO> buscarPorUsuario(Long usuarioId) {
        return auditoriaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toAuditoriaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditoriaResponseDTO> buscarPorOperacion(Auditoria.TipoOperacion operacion) {
        return auditoriaRepository.findByOperacion(operacion)
                .stream()
                .map(this::toAuditoriaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditoriaResponseDTO> buscarPorUsuarioYOperacion(Long usuarioId, Auditoria.TipoOperacion operacion) {
        return auditoriaRepository.findByUsuarioIdAndOperacion(usuarioId, operacion)
                .stream()
                .map(this::toAuditoriaDTO)
                .collect(Collectors.toList());
    }
}