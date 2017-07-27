CREATE DATABASE AlquilerDeVehiculos;
USE AlquilerDeVehiculos;

CREATE TABLE Proveedor (
  id_proveedor INT,
  nombre VARCHAR(40),
  direccion VARCHAR(40),
  pais VARCHAR(30),
  ciudad VARCHAR(30),
  telefono VARCHAR(10),
  e_mail VARCHAR(30),
  PRIMARY KEY (id_proveedor));

CREATE TABLE Vehiculo (
  matricula VARCHAR(10),
  proveedor  INT,
  tipo   VARCHAR(20),
  marca VARCHAR(20),
  año INT,
  nombre_modelo VARCHAR(20),
  disponibilidad BOOLEAN,
  color VARCHAR (20),
  capacidad INT,
  precio  FLOAT,
  foto LONGBLOB,
  PRIMARY KEY (matricula),
  FOREIGN KEY (proveedor) REFERENCES Proveedor(id_proveedor));

CREATE TABLE Empleado (
  id_empleado INT,
  id_departamento INT,
  cedula VARCHAR(10),
  usuario VARCHAR(15),
  contrasena VARCHAR(16),
  sueldo FLOAT,
  fecha_inicio DATETIME,
  puesto VARCHAR(20),
  grupo_trabajo VARCHAR(30),
  hora_entrada TIME,
  hora_salida TIME,
  telefono VARCHAR(10),
  direccion VARCHAR(40),
  PRIMARY KEY (id_empleado));

CREATE TABLE Departamento (
  id_departamento INT,
  id_supervisor INT,
  nombre  VARCHAR(20),
  PRIMARY KEY (id_departamento),
  FOREIGN KEY (id_supervisor) REFERENCES Empleado(id_empleado));
  
CREATE TABLE Reparacion (
  idReparacion INT,
  empleado INT,
  vehiculo VARCHAR(10),
  costo FLOAT,
  fecha_reparación DATETIME,
  descripcion VARCHAR(256),
  PRIMARY KEY (idReparacion),
  FOREIGN KEY (empleado) REFERENCES Empleado(id_empleado),
  FOREIGN KEY (vehiculo) REFERENCES Vehiculo(matricula));
  
CREATE TABLE Cuidado (
  empleado INT,
  vehiculo VARCHAR(10),
  observaciones VARCHAR(1024),
  PRIMARY KEY (empleado, vehiculo),
  FOREIGN KEY (empleado) REFERENCES Empleado(id_empleado),
  FOREIGN KEY (vehiculo) REFERENCES Vehiculo(matricula));

CREATE TABLE Cliente (
  id_cliente INT,
  ruc_ci BOOLEAN,
  identificacion VARCHAR(20),
  nombre  VARCHAR(40),
  telefono VARCHAR(10),
  direccion VARCHAR(40),
  razon_social VARCHAR(128),
  PRIMARY KEY (id_cliente));
  
CREATE TABLE AlquilerVehiculo (
  idAlquilerVehiculo INT,
  numero_dias INT,
  fecha_salida DATETIME,
  fecha_llegada DATETIME,
  cliente INT,
  vehiculo  VARCHAR(10),
  kilometraje_salida INT,
  nivel_gasolina_salida INT,
  PRIMARY KEY (idAlquilerVehiculo),
  FOREIGN KEY(cliente) REFERENCES Cliente(id_cliente),
  FOREIGN KEY(vehiculo) REFERENCES Vehiculo(matricula));

CREATE TABLE TipoPago (
  id_pago INT,
  idAlquilerVehiculo INT,
  fecha_pago DATETIME,
  valor_pago FLOAT,
  PRIMARY KEY (id_pago),
  FOREIGN KEY(idAlquilerVehiculo) REFERENCES AlquilerVehiculo(idAlquilerVehiculo));
  
ALTER TABLE Empleado
ADD CONSTRAINT FOREIGN KEY(id_departamento) REFERENCES Departamento(id_departamento);







