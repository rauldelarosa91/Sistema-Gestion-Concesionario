-- 1. Crear la base de datos
CREATE DATABASE IF NOT EXISTS concesionario;
USE concesionario;

-- 2. Tabla para la gestión de vehículos
CREATE TABLE IF NOT EXISTS coches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

-- 3. Tabla para la gestión de clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL
);

-- 4. Datos de prueba opcionales
INSERT INTO coches (marca, modelo, precio) VALUES ('Toyota', 'Corolla', 25000.00);
INSERT INTO clientes (nombre, apellido, email) VALUES ('Juan', 'Pérez', 'juan.perez@email.com');
