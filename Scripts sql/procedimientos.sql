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


drop procedure if exists getNombreProveedor;


delimiter |

CREATE PROCEDURE getNombreProveedor (IN idProveedorI INT,OUT nombreProveedorO VARCHAR(80))
BEGIN
	SELECT nombre INTO nombreProveedorO
    FROM proveedor
	WHERE id_proveedor=idProveedorI;

end |

delimiter ;
