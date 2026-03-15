package com.s1.proyecto_spring.service.impl;

import com.s1.proyecto_spring.dto.request.MovimientosRequestDTO;
import com.s1.proyecto_spring.dto.response.*;
import com.s1.proyecto_spring.mapper.*;
import com.s1.proyecto_spring.model.*;
import com.s1.proyecto_spring.repository.*;
import com.s1.proyecto_spring.service.MovimientosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientosServiceImpl implements MovimientosService {

    private final MovimientoRepository movimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final BodegaRepository bodegaRepository;
    private final MovimientosMapper movimientosMapper;
    private final UsuarioMapper usuarioMapper;
    private final BodegaMapper bodegaMapper;
    private final RolMapper rolMapper;

    // helper: Usuario → UsuarioResponseDTO
    private UsuarioResponseDTO toUsuarioDTO(Usuario usuario) {
        RolResponseDTO rolDTO = rolMapper.entidadADTO(usuario.getRol());
        return usuarioMapper.entidadADTO(usuario, rolDTO);
    }

    // helper: Bodega → BodegaResponseDTO (puede ser null en ENTRADA o SALIDA)
    private BodegaResponseDTO toBodegaDTO(Bodega bodega) {
        if (bodega == null) return null;
        return bodegaMapper.entidadADTO(bodega, toUsuarioDTO(bodega.getEncargado()));
    }

    // helper: construir MovimientosResponseDTO completo
    private MovimientosResponseDTO toDTO(Movimientos m) {
        return movimientosMapper.entidadADTO(
                m,
                toUsuarioDTO(m.getUsuario()),
                toBodegaDTO(m.getBodegaOrigen()),    // null si es ENTRADA
                toBodegaDTO(m.getBodegaDestino()),   // null si es SALIDA
                Collections.emptyList()              // detalles se cargan por separado
        );
    }

    @Override
    public MovimientosResponseDTO guardar(MovimientosRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.usuarioId()));

        Bodega bodegaOrigen = dto.bodegaOrigenId() != null
                ? bodegaRepository.findById(dto.bodegaOrigenId())
                .orElseThrow(() -> new RuntimeException("Bodega origen no encontrada con id: " + dto.bodegaOrigenId()))
                : null;

        Bodega bodegaDestino = dto.bodegaDestinoId() != null
                ? bodegaRepository.findById(dto.bodegaDestinoId())
                .orElseThrow(() -> new RuntimeException("Bodega destino no encontrada con id: " + dto.bodegaDestinoId()))
                : null;

        Movimientos movimiento = movimientosMapper.DTOAEntidad(dto, usuario, bodegaOrigen, bodegaDestino);
        Movimientos guardado = movimientoRepository.save(movimiento);
        return toDTO(guardado);
    }

    @Override
    public MovimientosResponseDTO actualizar(MovimientosRequestDTO dto, Long id) {
        Movimientos movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado con id: " + id));

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.usuarioId()));

        Bodega bodegaOrigen = dto.bodegaOrigenId() != null
                ? bodegaRepository.findById(dto.bodegaOrigenId())
                .orElseThrow(() -> new RuntimeException("Bodega origen no encontrada con id: " + dto.bodegaOrigenId()))
                : null;

        Bodega bodegaDestino = dto.bodegaDestinoId() != null
                ? bodegaRepository.findById(dto.bodegaDestinoId())
                .orElseThrow(() -> new RuntimeException("Bodega destino no encontrada con id: " + dto.bodegaDestinoId()))
                : null;

        movimientosMapper.actualizarEntidadDesdeDTO(movimiento, dto, usuario, bodegaOrigen, bodegaDestino);
        Movimientos actualizado = movimientoRepository.save(movimiento);
        return toDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!movimientoRepository.existsById(id))
            throw new RuntimeException("Movimiento no encontrado con id: " + id);
        movimientoRepository.deleteById(id);
    }

    @Override
    public MovimientosResponseDTO buscarPorId(Long id) {
        Movimientos movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado con id: " + id));
        return toDTO(movimiento);
    }

    @Override
    public List<MovimientosResponseDTO> buscarTodos() {
        return movimientoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientosResponseDTO> buscarPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return movimientoRepository.findByFechaBetween(fechaInicio, fechaFin)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}