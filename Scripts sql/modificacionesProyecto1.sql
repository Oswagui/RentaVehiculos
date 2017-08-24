use AlquilerDeVehiculos;

ALTER TABLE Empleado
ADD COLUMN nombre VARCHAR(15),
ADD COLUMN apellido VARCHAR(15),
MODIFY puesto VARCHAR(128),
MODIFY grupo_trabajo VARCHAR(128),
MODIFY direccion VARCHAR(128);

ALTER TABLE Proveedor
MODIFY direccion VARCHAR(128),
MODIFY e_mail VARCHAR(64);

ALTER TABLE Cliente
MODIFY direccion VARCHAR(128);

INSERT INTO Proveedor
VALUES(1000000001,'AutoMall','Cdla. Guayaquil Av. Miguel H. Alcívar frente a Torres del NorteCdla. Guayaquil Av. Miguel H. Alcívar frente a Torres del Norte','Ecuador','Guayaquil','0997275212','asesordenegocios_vehiculos@hotmail.com');

INSERT INTO Vehiculo
VALUES('GSK-2068',1000000001,'Auto','Ford',2016,'Fusion',1,'Gris',5,42990.00,LOAD_FILE('C:/Users/Mercedes/Desktop/Autos/fusion.jpg')),
	  ('GPT-5698',1000000001,'Pesado-Camion','Chevrolet',2017,'FVZ 34T FORDWARD',0,'Blanco',3,100990.00,LOAD_FILE('C:/Users/Mercedes/Desktop/Autos/camionBlanco.jpeg'));


select * from vehiculo;


