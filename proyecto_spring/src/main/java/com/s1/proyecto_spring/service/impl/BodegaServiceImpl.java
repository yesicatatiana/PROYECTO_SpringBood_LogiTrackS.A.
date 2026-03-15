package com.s1.proyecto_spring.service.impl;

import com.s1.proyecto_spring.dto.request.BodegaRequestDTO;
import com.s1.proyecto_spring.dto.response.BodegaResponseDTO;
import com.s1.proyecto_spring.dto.response.RolResponseDTO;
import com.s1.proyecto_spring.dto.response.UsuarioResponseDTO;
import com.s1.proyecto_spring.mapper.BodegaMapper;
import com.s1.proyecto_spring.mapper.RolMapper;
import com.s1.proyecto_spring.mapper.UsuarioMapper;
import com.s1.proyecto_spring.model.Bodega;
import com.s1.proyecto_spring.model.Usuario;
import com.s1.proyecto_spring.repository.BodegaRepository;
import com.s1.proyecto_spring.repository.UsuarioRepository;
import com.s1.proyecto_spring.service.BodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;
    private final UsuarioRepository usuarioRepository;
    private final BodegaMapper bodegaMapper;
    private final UsuarioMapper usuarioMapper;
    private final RolMapper rolMapper;

    // método helper para convertir Usuario → UsuarioResponseDTO
    private UsuarioResponseDTO toUsuarioDTO(Usuario usuario) {
        RolResponseDTO rolDTO = rolMapper.entidadADTO(usuario.getRol());
        return usuarioMapper.entidadADTO(usuario, rolDTO);
    }

    @Override
    public BodegaResponseDTO guardar(BodegaRequestDTO dto) {
        Usuario encargado = usuarioRepository.findById(dto.encargadoId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.encargadoId()));

        Bodega bodega = bodegaMapper.DTOAEntidad(dto, encargado);
        Bodega guardada = bodegaRepository.save(bodega);

        return bodegaMapper.entidadADTO(guardada, toUsuarioDTO(guardada.getEncargado()));
    }

    @Override
    public BodegaResponseDTO actualizar(BodegaRequestDTO dto, Long id) {
        Bodega bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + id));

        Usuario encargado = usuarioRepository.findById(dto.encargadoId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.encargadoId()));

        bodegaMapper.actualizarEntidadDesdeDTO(bodega, dto, encargado);
        Bodega actualizada = bodegaRepository.save(bodega);

        return bodegaMapper.entidadADTO(actualizada, toUsuarioDTO(actualizada.getEncargado()));
    }

    @Override
    public void eliminar(Long id) {
        if (!bodegaRepository.existsById(id))
            throw new RuntimeException("Bodega no encontrada con id: " + id);
        bodegaRepository.deleteById(id);
    }

    @Override
    public BodegaResponseDTO buscarPorId(Long id) {
        Bodega bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + id));

        return bodegaMapper.entidadADTO(bodega, toUsuarioDTO(bodega.getEncargado()));
    }

    @Override
    public List<BodegaResponseDTO> buscarTodos() {
        return bodegaRepository.findAll()
                .stream()
                .map(b -> bodegaMapper.entidadADTO(b, toUsuarioDTO(b.getEncargado())))
                .collect(Collectors.toList());
    }

    @Override
    public BodegaResponseDTO activarDesactivar(Long id, Boolean activo) {
        Bodega bodega = bodegaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con id: " + id));

        bodega.setActivo(activo);
        Bodega actualizada = bodegaRepository.save(bodega);

        return bodegaMapper.entidadADTO(actualizada, toUsuarioDTO(actualizada.getEncargado()));
    }
}