use alquilerdevehiculos;

/*procedimiento pra el login*/
drop procedure if exists login;
delimiter //
create procedure login (in usuario varchar(50), in contrasenia varchar(50), out numUsuario int, out rol varchar(45), out idUser int)
begin 
	select count(id_empleado), puesto, id_empleado into numUsuario, rol, idUser
    from empleado
    where usuario = empleado.usuario and contrasenia = empleado.contrasena;
end//

delimiter ;