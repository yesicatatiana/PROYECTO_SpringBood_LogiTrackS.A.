package com.s1.proyecto_spring.service.impl;

import com.s1.proyecto_spring.dto.response.RolResponseDTO;
import com.s1.proyecto_spring.mapper.RolMapper;
import com.s1.proyecto_spring.model.Rol;
import com.s1.proyecto_spring.repository.RolRepository;
import com.s1.proyecto_spring.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

        private final RolRepository rolRepository;
        private final RolMapper rolMapper;

        @Override
        public RolResponseDTO buscarPorId(Long id) {
            Rol rol = rolRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
            return rolMapper.entidadADTO(rol);
        }

        @Override
        public List<RolResponseDTO> buscarTodos() {
            return rolRepository.findAll()
                    .stream()
                    .map(rolMapper::entidadADTO)
                    .collect(Collectors.toList());
        }

}
