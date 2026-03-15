package com.s1.proyecto_spring.service;

import com.s1.proyecto_spring.dto.request.ProductoRequestDTO;
import com.s1.proyecto_spring.dto.response.ProductoResponseDTO;
import java.util.List;

public interface ProductoService {
    ProductoResponseDTO guardar(ProductoRequestDTO dto);
    ProductoResponseDTO actualizar(ProductoRequestDTO dto, Long id);
    void eliminar(Long id);
    ProductoResponseDTO buscarPorId(Long id);
    List<ProductoResponseDTO> buscarTodos();
    List<ProductoResponseDTO> buscarStockBajo(Integer stock);
}