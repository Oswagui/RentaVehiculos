use alquilerdevehiculos;

/*procedimiento pra el login*/
drop procedure if exists login;
delimiter //
create procedure login (in usuario varchar(50), in contrasenia varchar(50), out numUsuario int, out rol varchar(128), out idUser int)
begin 
	select count(id_empleado), puesto, id_empleado into numUsuario, rol, idUser
    from empleado
    where usuario = empleado.cedula and contrasenia = empleado.contrasena and empleado.contrasena is not null;
end//

delimiter ;

select * from cliente;

drop procedure if exists consultarCliente;

delimiter |
CREATE PROCEDURE consultarCliente (IN tipoI TINYINT, IN identificacionI VARCHAR(50), IN nombreI VARCHAR(50), IN rSocialI VARCHAR(256), 
IN rDesdeI INT, IN rHastaI INT, IN dgDesdeI INT, IN dgHastaI INT)
BEGIN
	SELECT c.id_cliente, c.identificacion, c.ruc_ci, c.nombre, c.telefono, c.direccion, c.razon_social 
    FROM cliente c
    WHERE (c.ruc_ci=tipoI OR tipoI IS NULL) AND (c.identificacion=identificacionI OR identificacionI IS NULL) AND 
    (c.nombre=nombreI OR nombreI IS NULL) AND (c.razon_social=rSocialI OR rSocialI IS NULL)
    AND EXISTS(
    SELECT c.id_cliente, c.identificacion, c.ruc_ci, c.nombre, c.telefono, c.direccion, c.razon_social 
    FROM cliente c LEFT JOIN alquilervehiculo a ON c.id_cliente=a.cliente
    GROUP BY c.id_cliente
    HAVING ((COUNT(a.idAlquilerVehiculo)>=rDesdeI OR rDesdeI IS NULL) AND (COUNT(a.idAlquilerVehiculo)<=rHastaI OR rHastaI IS NULL))
    )
    AND EXISTS(
	SELECT c.id_cliente, c.identificacion, c.ruc_ci, c.nombre, c.telefono, c.direccion, c.razon_social 
    FROM cliente c LEFT JOIN alquilervehiculo a ON c.id_cliente=a.cliente 
    LEFT JOIN tipopago t ON a.idAlquilerVehiculo=t.idAlquilerVehiculo
    GROUP BY c.id_cliente
    HAVING((SUM(t.valor_pago)>=dgDesdeI OR dgDesdeI IS NULL OR SUM(t.valor_pago) IS NULL) AND (SUM(t.valor_pago)<=dgHastaI OR 
    dgHastaI IS NULL OR SUM(t.valor_pago) IS NULL))
    );
end|

delimiter ;


select * from vehiculo; 

drop procedure if exists consultarVehiculo;

delimiter |
CREATE PROCEDURE consultarVehiculo (IN matriculaI VARCHAR(50), IN tipoI VARCHAR(50), IN marcaI VARCHAR(50), IN nombre_modeloI VARCHAR(50), 
IN colorI VARCHAR(50), IN aDesdeI INT, IN aHastaI INT, IN cDesdeI INT, IN cHastaI INT, IN pDesdeI FLOAT, IN pHastaI FLOAT)
BEGIN
	SELECT v.matricula, v.proveedor, v.tipo, v.marca, v.año, v.nombre_modelo, v.disponibilidad, v.color, v.capacidad, v.precio, v.foto
    FROM Vehiculo v
    WHERE (v.matricula=matriculaI OR matriculaI IS NULL) AND (v.tipo=tipoI OR tipoI IS NULL) AND (v.marca=marcaI OR marcaI IS NULL) AND 
    (v.nombre_modelo=nombre_modeloI OR nombre_modeloI IS NULL) AND (v.color=colorI OR colorI IS NULL) AND (v.año>=aDesdeI OR aDesdeI IS NULL)
    AND (v.año<=aHastaI OR aHastaI IS NULL) AND (v.capacidad>=cDesdeI OR cDesdeI IS NULL) AND (v.capacidad<=cHastaI OR cHastaI IS NULL)
    AND (v.precio>=pDesdeI OR pDesdeI IS NULL) AND (v.precio<=pHastaI OR pHastaI IS NULL);
    
end|

delimiter ;




delimiter |

CREATE PROCEDURE getNombreProveedor(IN idProveedorI INT,OUT nombreProveedorO VARCHAR(80))
BEGIN
	SELECT nombre INTO nombreProveedorO
    FROM proveedor
	WHERE id_proveedor=idProveedorI;

end |

delimiter ;



delimiter |

CREATE PROCEDURE insertarCliente (IN id_clienteI INT, IN ruc_ciI TINYINT, IN identificacionI VARCHAR(50), IN nombreI VARCHAR(50), 
IN telefonoI VARCHAR(20), IN direccionI VARCHAR(256), IN rSocialI VARCHAR(256))
BEGIN
	INSERT INTO cliente VALUES
	(id_clienteI, ruc_ciI, idenificacionI, nombreI, telefonoI, direccionI, razon_socialI);
END |

delimiter ;



delimiter |

CREATE PROCEDURE insertarVehiculo (IN matriculaI VARCHAR(20), IN proveedorI INT, IN tipoI VARCHAR(50), IN marcaI VARCHAR(50),
IN añoI INT, IN nombre_modeloI VARCHAR(20), IN disponibilidadI TINYTEXT, IN colorI VARCHAR(256), IN precioI FLOAT)
BEGIN
	INSERT INTO vehiculo VALUES
	(matriculaI, proveedorI, tipoI, marcaI, añoI, nombre_modeloI, disponibilidadI, colorI, precioI);
END |


delimiter ;



delimiter |

CREATE PROCEDURE insertarEmpleado(IN id_empleadoI INT,  IN id_departamentoI INT, IN cedulaI VARCHAR(20), IN contrasenaI VARCHAR(20),
IN sueldoI FLOAT, IN fecha_inicioI DATETIME, IN puestoI VARCHAR(256), IN grupoTrabajoI VARCHAR(256), IN hora_entradaI TIME, 
IN hora_salidaI TIME, IN telfonoI VARCHAR(20), IN direccionI VARCHAR(256), IN nombreI VARCHAR(25), IN apellido VARCHAR(25))
BEGIN
	INSERT INTO vehiculo VALUES
	(id_empleadoI, id_departamentoI, cedulaI, contrasenaI, sueldoI, fecha_inicioI, puestoI, grupoTrabajoI, hora_entradaI, 
	hora_salidaI, telfonoI, direccionI, nombreI, apellido);
END |

delimiter ;



delimiter |

CREATE PROCEDURE insertarDepartamento(IN id_departamentoI INT, IN id_supervisorI INT, nombreI VARCHAR(50))
BEGIN
	INSERT INTO vehiculo VALUES
	(departamentoI, id_supervisorI, nombreI);
END |

delimiter ;



delimiter |

CREATE PROCEDURE insertarCuidado(IN empleadoI INT, IN vehiculoI VARCHAR(20), observacionesI VARCHAR(2046))
BEGIN
	INSERT INTO vehiculo VALUES
	(empladoI, vehiculoI, observacionesI);
END |

delimiter ;




delimiter |

CREATE PROCEDURE insertarReparacion(IN idReparacionI INT,IN empleadoI INT, IN vehiculoI VARCHAR(20), IN costoI FLOAT, 
IN fecha_reparacionI DATETIME, IN descripcionI VARCHAR(512))
BEGIN
	INSERT INTO vehiculo VALUES
	(idReparacionI,empladoI, vehiculoI, costoI, fecha_reparacionI, descripcionI);
END |

delimiter ;




delimiter |

CREATE PROCEDURE insertarProveedor(IN id_proveedorI INT, IN nombreI VARCHAR(80), IN direccionI VARCHAR(256), IN paisI VARCHAR(60), 
IN ciudadI VARCHAR(60), IN telefonoI VARCHAR(20), IN rSocialI VARCHAR(256))
BEGIN
	INSERT INTO cliente VALUES
	(id_proveedorI, nombreI, direccionI, paisI, ciudadI, telefonoI, rSocialI);
END |

delimiter ;




delimiter |

CREATE PROCEDURE modificarDisponibilidadVehiculo(IN matriculaI VARCHAR(20), IN disponibilidadI TINYINT)
BEGIN
	UPDATE vehiculo 
	SET disponibilidad=disponibilidadI
    WHERE matricula=matriculaI;
END |

delimiter ;




delimiter |
CREATE PROCEDURE consultarReparaciones(IN cedulaEmpleadoI INT, IN vehiculoI VARCHAR(20), IN costoI FLOAT, IN fecha_reparacionI DATETIME)
BEGIN
	SELECT e.nombre as Nombre_Empleado, e.apellido as Apellido_Empleado, v.matricula as Placa_Vehiculo, r.costo as Costo_Reparacion,
	r.fecha_reparacion as Fecha_Rep_, r.descripcion as Descripcion_Reparación
	FROM Reparacion r JOIN Empleado e ON r.empleado=e.id_empleado
	JOIN Vehiculo v ON r.vehiculo=v.matricula
	WHERE (r.costo=costoI OR costoI IS NULL) AND (r.fecha_reparacion=fecha_reparacionI OR fecha_reparacionI IS NULL) AND 
	(v.matricula=vehiculoI OR vehiculoI IS NULL) AND (e.cedula=cedulaEmpleadoI OR cedulaEmpleadoI IS NULL);
		
	
end|

delimiter ;




delimiter |
CREATE PROCEDURE consultarCuidados(IN cedulaEmpleadoI INT, IN vehiculoI VARCHAR(20))
BEGIN
	SELECT e.nombre as Nombre_Empleado, e.apellido as Apellido_Empleado, v.matricula as Placa_Vehiculo,
    c.observaciones as Observaciones_Cuidado
	FROM Cuidado c JOIN Empleado e ON c.empleado=e.id_empleado
	JOIN Vehiculo v ON c.vehiculo=v.matricula
	WHERE (v.matricula=vehiculoI OR vehiculoI IS NULL) AND (e.cedula=cedulaEmpleadoI OR cedulaEmpleadoI IS NULL);
	
end|

delimiter ;




drop procedure if exists consultarReparaciones;
















