package com.s1.proyecto_spring.service.impl;

import com.s1.proyecto_spring.dto.request.ProductoRequestDTO;
import com.s1.proyecto_spring.dto.response.ProductoResponseDTO;
import com.s1.proyecto_spring.mapper.ProductoMapper;
import com.s1.proyecto_spring.model.Producto;
import com.s1.proyecto_spring.repository.ProductoRepository;
import com.s1.proyecto_spring.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoResponseDTO guardar(ProductoRequestDTO dto) {
        Producto producto = productoMapper.DTOAEntidad(dto);
        Producto guardado = productoRepository.save(producto);
        return productoMapper.entidadADTO(guardado);
    }

    @Override
    public ProductoResponseDTO actualizar(ProductoRequestDTO dto, Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        productoMapper.actualizarEntidadDesdeDTO(producto, dto);
        Producto actualizado = productoRepository.save(producto);
        return productoMapper.entidadADTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id))
            throw new RuntimeException("Producto no encontrado con id: " + id);
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return productoMapper.entidadADTO(producto);
    }

    @Override
    public List<ProductoResponseDTO> buscarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::entidadADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoResponseDTO> buscarStockBajo(Integer stock) {
        return productoRepository.findByStockLessThan(stock)
                .stream()
                .map(productoMapper::entidadADTO)
                .collect(Collectors.toList());
    }
}