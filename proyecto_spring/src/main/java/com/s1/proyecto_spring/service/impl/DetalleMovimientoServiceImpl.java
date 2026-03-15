package com.s1.proyecto_spring.service.impl;

import com.s1.proyecto_spring.dto.request.DetalleMovimientoRequestDTO;
import com.s1.proyecto_spring.dto.response.BodegaResponseDTO;
import com.s1.proyecto_spring.dto.response.DetalleMovimientoResponseDTO;
import com.s1.proyecto_spring.dto.response.MovimientosResponseDTO;
import com.s1.proyecto_spring.dto.response.ProductoResponseDTO;
import com.s1.proyecto_spring.dto.response.RolResponseDTO;
import com.s1.proyecto_spring.dto.response.UsuarioResponseDTO;
import com.s1.proyecto_spring.mapper.*;
import com.s1.proyecto_spring.model.*;
import com.s1.proyecto_spring.repository.*;
import com.s1.proyecto_spring.service.DetalleMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetalleMovimientoServiceImpl implements DetalleMovimientoService {

    private final DetalleMovimientoRepository detalleMovimientoRepository;
    private final MovimientoRepository movimientosRepository;
    private final ProductoRepository productoRepository;
    private final DetalleMovimientoMapper detalleMovimientoMapper;
    private final MovimientosMapper movimientosMapper;
    private final ProductoMapper productoMapper;
    private final UsuarioMapper usuarioMapper;
    private final BodegaMapper bodegaMapper;
    private final RolMapper rolMapper;

    // helper: Usuario → UsuarioResponseDTO
    private UsuarioResponseDTO toUsuarioDTO(Usuario usuario) {
        RolResponseDTO rolDTO = rolMapper.entidadADTO(usuario.getRol());
        return usuarioMapper.entidadADTO(usuario, rolDTO);
    }

    // helper: Bodega → BodegaResponseDTO
    private BodegaResponseDTO toBodegaDTO(Bodega bodega) {
        return bodegaMapper.entidadADTO(bodega, toUsuarioDTO(bodega.getEncargado()));
    }

    // helper: Producto → ProductoResponseDTO
    private ProductoResponseDTO toProductoDTO(Producto producto) {
        return productoMapper.entidadADTO(producto);
    }

    // helper: Movimientos → MovimientosResponseDTO
    private MovimientosResponseDTO toMovimientoDTO(Movimientos movimiento) {
        UsuarioResponseDTO usuarioDTO = toUsuarioDTO(movimiento.getUsuario());
        BodegaResponseDTO origenDTO = movimiento.getBodegaOrigen() != null
                ? toBodegaDTO(movimiento.getBodegaOrigen()) : null;
        BodegaResponseDTO destinoDTO = movimiento.getBodegaDestino() != null
                ? toBodegaDTO(movimiento.getBodegaDestino()) : null;
        return movimientosMapper.entidadADTO(movimiento, usuarioDTO, origenDTO, destinoDTO, null);
    }

    // helper: DetalleMovimiento → DetalleMovimientoResponseDTO
    private DetalleMovimientoResponseDTO toDetalleDTO(DetalleMovimiento detalle) {
        return detalleMovimientoMapper.entidadADTO(detalle, toProductoDTO(detalle.getProducto()));
    }

    @Override
    public DetalleMovimientoResponseDTO guardar(DetalleMovimientoRequestDTO dto) {
        Movimientos movimiento = movimientosRepository.findById(dto.movimientoId())
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado con id: " + dto.movimientoId()));

        Producto producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + dto.productoId()));

        DetalleMovimiento detalle = detalleMovimientoMapper.DTOAEntidad(dto, movimiento, producto);
        DetalleMovimiento guardado = detalleMovimientoRepository.save(detalle);
        return toDetalleDTO(guardado);
    }

    @Override
    public DetalleMovimientoResponseDTO actualizar(DetalleMovimientoRequestDTO dto, Long id) {
        DetalleMovimiento detalle = detalleMovimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con id: " + id));

        Movimientos movimiento = movimientosRepository.findById(dto.movimientoId())
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado con id: " + dto.movimientoId()));

        Producto producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + dto.productoId()));

        detalleMovimientoMapper.actualizarEntidadDesdeDTO(detalle, dto, movimiento, producto);
        DetalleMovimiento actualizado = detalleMovimientoRepository.save(detalle);
        return toDetalleDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!detalleMovimientoRepository.existsById(id))
            throw new RuntimeException("Detalle no encontrado con id: " + id);
        detalleMovimientoRepository.deleteById(id);
    }

    @Override
    public DetalleMovimientoResponseDTO buscarPorId(Long id) {
        DetalleMovimiento detalle = detalleMovimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con id: " + id));
        return toDetalleDTO(detalle);
    }

    @Override
    public List<DetalleMovimientoResponseDTO> buscarTodos() {
        return detalleMovimientoRepository.findAll()
                .stream()
                .map(this::toDetalleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetalleMovimientoResponseDTO> buscarPorMovimiento(Long movimientoId) {
        return detalleMovimientoRepository.findByMovimientoId(movimientoId)
                .stream()
                .map(this::toDetalleDTO)
                .collect(Collectors.toList());
    }
}