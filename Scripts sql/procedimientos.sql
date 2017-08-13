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
    UNION
    SELECT c.id_cliente, c.identificacion, c.ruc_ci, c.nombre, c.telefono, c.direccion, c.razon_social 
    FROM cliente c JOIN alquilervehiculo a ON c.id_cliente=a.cliente
    GROUP BY c.id_cliente
    HAVING ((COUNT(a.idAlquilerVehiculo)>=rDesdeI OR rDesdeI IS NULL) AND (COUNT(a.idAlquilerVehiculo)<=rHastaI OR rHastaI IS NULL))
    UNION
	SELECT c.id_cliente, c.identificacion, c.ruc_ci, c.nombre, c.telefono, c.direccion, c.razon_social 
    FROM cliente c JOIN alquilervehiculo a ON c.id_cliente=a.cliente 
    JOIN tipopago t ON a.idAlquilerVehiculo=t.idAlquilerVehiculo
    GROUP BY c.id_cliente
    HAVING((SUM(t.valor_pago)>=dgDesdeI OR dgDesdeI IS NULL) AND (SUM(t.valor_pago)<=dgHastaI OR dgHastaI IS NULL));
end|

delimiter ;
