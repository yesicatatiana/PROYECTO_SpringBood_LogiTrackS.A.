package com.s1.proyecto_spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_movimiento")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Detalle_movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private BigDecimal precio_unitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimiento_id", nullable = false)
    private Movimientos movimiento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
}
