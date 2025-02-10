-- Crear BBDD
CREATE DATABASE PruebaMercadona;

-- Crear tabla usuarios
CREATE TABLE pruebamercadona.usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL
);

-- Crear tabla de productos
CREATE TABLE pruebamercadona.productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    cantidad INT NOT NULL
);