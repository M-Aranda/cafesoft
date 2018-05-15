CREATE DATABASE cafesoft;

USE cafesoft;


CREATE TABLE tipo_vivienda(
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE TABLE tipo_usuario(
    id INT AUTO_INCREMENT,
    nombre VARCHAR(20),
    PRIMARY KEY(id)
);

CREATE TABLE cliente(
    run VARCHAR(15),
    nombre VARCHAR(50),
    sueldo INT,
    PRIMARY KEY(run)
);

CREATE TABLE vivienda(
    n_rol INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    tipo_fk INT,
    precio_arriendo INT,
    precio_venta INT,
    cant_baños INT,
    cant_piezas INT,
    direccion VARCHAR(50),
    condicion VARCHAR(50),
    PRIMARY KEY(n_rol),
    FOREIGN KEY(tipo_fk) REFERENCES tipo_vivienda(id)
);

CREATE TABLE usuario(
    run VARCHAR(15),
    nombre VARCHAR(50),
    tipo_fk INT,
    PRIMARY KEY(run),
    FOREIGN KEY(tipo_fk) REFERENCES tipo_usuario(id)
);

CREATE TABLE log(
    id INT AUTO_INCREMENT,
    descripcion VARCHAR(50),
    fecha DATE,
    usuario_fk VARCHAR(15),
    PRIMARY KEY (id),
    FOREIGN KEY(usuario_fk) REFERENCES usuario(run)
);

CREATE TABLE venta(
    id INT AUTO_INCREMENT,
    cliente_fk VARCHAR(15),
    usuario_fk VARCHAR(15),
    vivienda_fk INT,
    fecha DATE,
    PRIMARY KEY(id),
    FOREIGN KEY(cliente_fk) REFERENCES cliente(run),
    FOREIGN KEY(usuario_fk) REFERENCES usuario(run),
    FOREIGN KEY(vivienda_fk) REFERENCES vivienda(n_rol)
);