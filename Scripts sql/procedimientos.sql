use alquilerdevehiculos;

/*procedimiento pra el login*/
drop procedure if exists login;
delimiter //
create procedure login (in usuario varchar(50), in contrasenia varchar(50), out numUsuario int, out rol varchar(128), out idUser int)
begin 
	select count(id_empleado), puesto, id_empleado into numUsuario, rol, idUser
    from empleado
    where usuario = empleado.usuario and contrasenia = empleado.contrasena and empleado.contrasena is not null;
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


drop procedure if exists getNombreProveedor;


delimiter |

CREATE PROCEDURE getNombreProveedor(IN idProveedorI INT,OUT nombreProveedorO VARCHAR(80))
BEGIN
	SELECT nombre INTO nombreProveedorO
    FROM proveedor
	WHERE id_proveedor=idProveedorI;

end |

delimiter ;


drop procedure if exists insertarCliente;
delimiter |

CREATE PROCEDURE insertarCliente (IN ruc_ciI TINYINT, IN identificacionI VARCHAR(50), IN nombreI VARCHAR(50), 
IN telefonoI VARCHAR(20), IN direccionI VARCHAR(256), IN rSocialI VARCHAR(256))
BEGIN
	INSERT INTO cliente VALUES
	(ruc_ciI, idenificacionI, nombreI, telefonoI, direccionI, razon_socialI);
END |

delimiter ;


drop procedure if exists insertarVehiculo;
delimiter |

CREATE PROCEDURE insertarVehiculo (IN matriculaI VARCHAR(20), IN proveedorI VARCHAR(50), IN tipoI VARCHAR(50), IN marcaI VARCHAR(50),
IN añoI INT, IN nombre_modeloI VARCHAR(20), IN colorI VARCHAR(256), IN capacidadI INT, IN precioI FLOAT)
BEGIN
	INSERT INTO vehiculo(matricula, tipo, marca, año, nombre_modelo, disponibilidad, color, capacidad,precio) VALUES
	(matriculaI, tipoI, marcaI, añoI, nombre_modeloI, 1, colorI, capacidad,precioI);
    UPDATE vehiculo
    SET proveedor=(SELECT p.id_proveedor FROM vehiculo v join proveedor p on v.proveedor=p.id_proveedor WHERE p.nombre=proveedorI)
    WHERE matricula=matriculaI;
END |


delimiter ;


drop procedure if exists insertarEmpleado;
delimiter |

CREATE PROCEDURE insertarEmpleado(IN nombreI VARCHAR(25), IN apellidoI VARCHAR(25), IN cedulaI VARCHAR(20), IN usuarioI VARCHAR(20),
IN contrasenaI VARCHAR(20), IN sueldoI FLOAT, IN fecha_inicioI DATETIME, IN puestoI VARCHAR(256), IN grupoTrabajoI VARCHAR(256), 
IN hora_entradaI TIME, IN hora_salidaI TIME, IN telefonoI VARCHAR(20), IN direccionI VARCHAR(256))
BEGIN
	INSERT INTO vehiculo(nombre, apellido, cedula, usuario, contrasena, sueldo, fecha_inicio, puesto, grupoTrabajo, hora_entrada, 
	hora_salida, telefono, direccion) VALUES
	(nombreI, apellidoI, cedulaI, usuarioI, contrasenaI, sueldoI, fecha_inicioI, puestoI, grupoTrabajoI, hora_entradaI, 
	hora_salidaI, telefonoI, direccionI);
END |

delimiter ;


drop procedure if exists insertarDepartamento;
delimiter |

CREATE PROCEDURE insertarDepartamento(IN id_departamentoI INT, IN id_supervisorI INT, nombreI VARCHAR(50))
BEGIN
	INSERT INTO vehiculo VALUES
	(departamentoI, id_supervisorI, nombreI);
END |

delimiter ;


drop procedure if exists insertarCuidado;
delimiter |

CREATE PROCEDURE insertarCuidado(IN empleadoI INT, IN vehiculoI VARCHAR(20), observacionesI VARCHAR(2046))
BEGIN
	INSERT INTO vehiculo VALUES
	(empladoI, vehiculoI, observacionesI);
END |

delimiter ;



drop procedure if exists insertarReparacion;
delimiter |

CREATE PROCEDURE insertarReparacion(IN idReparacionI INT,IN empleadoI INT, IN vehiculoI VARCHAR(20), IN costoI FLOAT, 
IN fecha_reparacionI DATETIME, IN descripcionI VARCHAR(512))
BEGIN
	INSERT INTO vehiculo VALUES
	(idReparacionI,empladoI, vehiculoI, costoI, fecha_reparacionI, descripcionI);
END |

delimiter ;



drop procedure if exists insertarProveedor;
delimiter |

CREATE PROCEDURE insertarProveedor(IN id_proveedorI INT, IN nombreI VARCHAR(80), IN direccionI VARCHAR(256), IN paisI VARCHAR(60), 
IN ciudadI VARCHAR(60), IN telefonoI VARCHAR(20), IN rSocialI VARCHAR(256))
BEGIN
	INSERT INTO cliente VALUES
	(id_proveedorI, nombreI, direccionI, paisI, ciudadI, telefonoI, rSocialI);
END |

delimiter ;



drop procedure if exists modificarDisponibilidadVehiculo;
delimiter |

CREATE PROCEDURE modificarDisponibilidadVehiculo(IN matriculaI VARCHAR(20), IN disponibilidadI TINYINT)
BEGIN
	UPDATE vehiculo 
	SET disponibilidad=disponibilidadI
    WHERE matricula=matriculaI;
END |

delimiter ;

drop procedure if exists obtenerColores;
delimiter |

CREATE PROCEDURE obtenerColores()
BEGIN
	SELECT distinct color
    from vehiculo
    order by color;
END |

delimiter ;


drop procedure if exists obtenerTipos;
delimiter |

CREATE PROCEDURE obtenerTipos()
BEGIN
	SELECT distinct tipo
    from vehiculo
    order by tipo;
END |


delimiter ;
drop procedure if exists consultarReparaciones;
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

drop procedure if exists consultarCuidados;
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


drop procedure if exists obtenerMatriculas;
delimiter |
CREATE PROCEDURE obtenerMatriculas()
BEGIN
	SELECT matricula
    from vehiculo
    order by matricula;
end|

delimiter ;


drop procedure if exists obtenerRangoAños;
delimiter |
CREATE PROCEDURE obtenerRangoAños(OUT maxAño int, OUT minAño int)
BEGIN
	SELECT max(año) , min(año) into maxAño,minAño
    from vehiculo;
end|

delimiter ;

drop procedure if exists obtenerRangoCapacidad;
delimiter |
CREATE PROCEDURE obtenerRangoCapacidad(OUT maxCapacidad int, OUT minCapacidad int)
BEGIN
	SELECT max(capacidad) , min(capacidad) into maxCapacidad,minCapacidad
    from vehiculo;
end|

delimiter ;


drop procedure if exists obtenerRangoPrecio;
delimiter |
CREATE PROCEDURE obtenerRangoPrecio(OUT maxPrecio float, OUT minPrecio float)
BEGIN
	SELECT max(precio) , min(precio) into maxPrecio,minPrecio
    from vehiculo;
end|

delimiter ;


drop procedure if exists rentar;

delimiter |
CREATE PROCEDURE rentar(IN numero_diasI INT, IN fecha_salidaI DATETIME, IN kilometraje_salidaI INT, 
IN nivel_gasolina_salidaI INT, IN placaVehiculoI VARCHAR(10), IN cedulaClienteI VARCHAR(20), IN valor_pagoI FLOAT)
BEGIN
	INSERT INTO alquilervehiculo(numero_dias, fecha_salida, vehiculo, kilometraje_salida, nivel_gasolina_salida)
    VALUES(numero_diasI, fecha_salidaI, placaVehiculoI,kilometraje_salidaI, nivel_gasolinaI);
    UPDATE alquilervehiculo
    SET cliente=(SELECT id_cliente FROM cliente WHERE cedula=cedulaClienteI)
    WHERE idAlquilerVehiculo=(SELECT max(idAlquilerVehiculo) FROM alquilervehiculo);
    INSERT INTO tipopago(idAlquilerVehiculo, fecha_pago, valor_pago)
    VALUES((SELECT max(idAlquilerVehiculo) FROM alquilervehiculo),fecha_salidaI, valor_pagoI);
    
END|

delimiter ;



drop trigger if EXISTS modificarDisponibilidadAfterAlquiler;

delimiter |
CREATE trigger modificarDisponibilidadAfterAlquiler after Insert on alquilerVehiculo
for each row
begin
	update vehiculo
    set disponibilidad = 0
    where vehiculo.matricula=new.vehiculo;
end|

delimiter ;



drop procedure if exists modificarVehiculo;
delimiter |

CREATE PROCEDURE modificarVehiculo (IN matriculaI VARCHAR(20), IN tipoI VARCHAR(50), IN marcaI VARCHAR(50),
IN añoI INT, IN nombre_modeloI VARCHAR(20), IN colorI VARCHAR(256), IN capacidadI INT, IN precioI FLOAT, IN imagenI LONGBLOB)
BEGIN
	UPDATE vehiculo
    SET tipo=tipoI, marca=marcaI, año=añoI, nombre_modelo=nombre_modeloI, color=colorI, capacidad=capacidadI,precio=precioI, foto=imagenI
	WHERE matricula=matriculaI;
END |


delimiter ;






