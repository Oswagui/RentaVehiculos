USE AlquilerDeVehiculos;

INSERT INTO Departamento
VALUES(100000000,NULL,'Atencion al Cliente');

INSERT INTO Empleado
VALUES(1000000001,NULL,0951603568,'admin1','soyadmin01',900.60,'2017-06-26','Adminin of DB',NULL,'08:00:00','19:00:00','0993235670','Capitán Najera & Los Ríos');
INSERT INTO Empleado
VALUES(1000000002,NULL,0968942645,'admin2','contra12k',900.60,'2017-06-30','Adminin of DB',NULL,':13:00','23:00:00','0989631152','Pedro Pablo Gómez y la 15');
INSERT INTO Empleado
VALUES(1000000011,100000000,0962532456,'serv3','contra25j',750.60,'2015-07-16','Servicio al Cliente',NULL,':07:00','17:00:00','0968946782','Ayacucho 72, Piso 01');
INSERT INTO Empleado
VALUES(1100000001,100000000,0965999987,'super3',NULL,800.60,'2015-07-16','Supervisor',NULL,':07:00','17:00:00','0968946782','Ayacucho 72, Piso 01');

UPDATE Departamento
SET id_supervisor=1100000001
WHERE nombre='Atencion al Cliente';


