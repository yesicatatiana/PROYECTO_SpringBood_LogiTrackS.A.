CREATE DATABASE logitrack;

USE logitrack;


CREATE TABLE rol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre enum('administrador','empleado')
);


CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL, 
    contrasena VARCHAR(255) NOT NULL,   
    activo BOOLEAN NOT NULL DEFAULT TRUE
); 


CREATE TABLE usuario_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    rol_id BIGINT NOT NULL,
    FOREIGN KEY(usuario_id) REFERENCES usuario(id),
    FOREIGN KEY(rol_id) REFERENCES rol(id)
);


CREATE TABLE bodega (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    ubicacion VARCHAR(200) NOT NULL,
    capacidad INT NOT NULL,  
    activo BOOLEAN NOT NULL,
    encargado_id BIGINT NOT NULL,
    FOREIGN KEY(encargado_id) REFERENCES usuario(id)
);


CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(200),
    precio DECIMAL(12, 2) NOT NULL,
    stock INT NOT NULL,
    categoria VARCHAR(150),
);


CREATE TABLE inventario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    stock INT NOT NULL DEFAULT 0,
    bodega_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    FOREIGN KEY (bodega_id) REFERENCES bodega(id),
    FOREIGN KEY (producto_id) REFERENCES producto(id)
); 

CREATE TABLE movimientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('ENTRADA','SALIDA','TRANSFERENCIA') NOT NULL,
    fecha DATETIME NOT NULL,
    estado ENUM('PENDIENTE','COMPLETADO','ANULADO') NOT NULL DEFAULT 'COMPLETADO',
    usuario_id BIGINT NOT NULL,
    bodega_origen_id  BIGINT,
    bodega_destino_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (bodega_origen_id) REFERENCES bodega(id),
    FOREIGN KEY (bodega_destino_id) REFERENCES bodega(id)
); 

CREATE TABLE detalle_movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,    
    cantidad INT NOT NULL,   
    precio_unitario DECIMAL(12, 2) NULL,
    movimiento_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    FOREIGN KEY (movimiento_id) REFERENCES movimientos(id),
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);


CREATE TABLE auditoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    operacion ENUM('INSERT','UPDATE','DELETE') NOT NULL,
    entidad VARCHAR(60) NOT NULL,
    valores_anteriores TEXT NOT NULL,  
    valores_nuevos TEXT NOT NULL,
    origen VARCHAR(45) NOT NULL, 
    fecha_hora DATETIME NOT NULL,
    usuario_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);