package com.s1.proyecto_spring.service.impl;

import com.s1.proyecto_spring.dto.request.UsuarioRequestDTO;
import com.s1.proyecto_spring.dto.response.RolResponseDTO;
import com.s1.proyecto_spring.dto.response.UsuarioResponseDTO;
import com.s1.proyecto_spring.mapper.RolMapper;
import com.s1.proyecto_spring.mapper.UsuarioMapper;
import com.s1.proyecto_spring.model.Rol;
import com.s1.proyecto_spring.model.Usuario;
import com.s1.proyecto_spring.repository.RolRepository;
import com.s1.proyecto_spring.repository.UsuarioRepository;
import com.s1.proyecto_spring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    private final RolMapper rolMapper;

    @Override
    public UsuarioResponseDTO guardar(UsuarioRequestDTO dto) {
        Rol rol = rolRepository.findById(dto.rolesId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + dto.rolesId()));

        Usuario usuario = usuarioMapper.DTOAEntidad(dto, rol);
        Usuario guardado = usuarioRepository.save(usuario);

        RolResponseDTO rolDTO = rolMapper.entidadADTO(guardado.getRol());
        return usuarioMapper.entidadADTO(guardado, rolDTO);
    }

    @Override
    public UsuarioResponseDTO actualizar(UsuarioRequestDTO dto, Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        Rol rol = rolRepository.findById(dto.rolesId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + dto.rolesId()));

        usuarioMapper.actualizarEntidadDesdeDTO(usuario, dto, rol);
        Usuario actualizado = usuarioRepository.save(usuario);

        RolResponseDTO rolDTO = rolMapper.entidadADTO(actualizado.getRol());
        return usuarioMapper.entidadADTO(actualizado, rolDTO);
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id))
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        RolResponseDTO rolDTO = rolMapper.entidadADTO(usuario.getRol());
        return usuarioMapper.entidadADTO(usuario, rolDTO);
    }

    @Override
    public List<UsuarioResponseDTO> buscarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(u -> {
                    RolResponseDTO rolDTO = rolMapper.entidadADTO(u.getRol());
                    return usuarioMapper.entidadADTO(u, rolDTO);
                })
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO activarDesactivar(Long id, Boolean activo) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        usuario.setActivo(activo);
        Usuario actualizado = usuarioRepository.save(usuario);

        RolResponseDTO rolDTO = rolMapper.entidadADTO(actualizado.getRol());
        return usuarioMapper.entidadADTO(actualizado, rolDTO);
    }
}