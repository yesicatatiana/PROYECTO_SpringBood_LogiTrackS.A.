package com.s1.proyecto_spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Collate;
import org.springframework.web.servlet.tags.form.TextareaTag;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('INSERTAR','ACTUALIZAR','ELIMINAR'")
    private auditoria operacion;

    @Column(nullable = false)
    private String entidad;

    @Column(name = "valores_anteriores", nullable = false, columnDefinition = "TEXT")
    private String valoresAnteriores;

    @Column(name = "valores_nuevos",nullable = false, columnDefinition = "TEXT")
    private String valoresNuevos;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
