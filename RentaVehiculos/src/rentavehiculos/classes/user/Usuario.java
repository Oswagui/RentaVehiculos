/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.classes.user;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import rentavehiculos.classes.connection.Conexion;

/**
 *
 * @author Oswaldo
 */
public class Usuario {
    
    private int idEmpleado;
    private int idDepartamento;
    private String usuario;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String puesto;

    public Usuario() {
    }

    public Usuario(int idEmpleado, int idDepartamento, String usuario, String nombre, String apellido, String contrasenia, String puesto) {
        this.idEmpleado = idEmpleado;
        this.idDepartamento = idDepartamento;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.puesto = puesto;
    }


    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public static ArrayList<String> buscarUsuario(String usuario, 
            String clave) {
        Conexion cn = new Conexion();
        
        ArrayList<String> validadorUsuario = new ArrayList<>();
        
        int existeU, idU;
        
        String rol = "";
        
        existeU = idU = -1;
        
        try{
            // Llamada al procedimiento almacenado
            CallableStatement cst = cn.getConnection().
                    prepareCall("{call  login(?,?,?,?,?)}");
            // Parametro 1 del procedimiento almacenado
            
            //String username= "fblayedr";
            cst.setString(1, usuario);
            //String contrasenia= "turtoise";
            cst.setString(2, clave);

            // Definimos los tipos de los parametros de salida del procedimiento almacenado
            cst.registerOutParameter(3, java.sql.Types.INTEGER);
            cst.registerOutParameter(4, java.sql.Types.VARCHAR);
            cst.registerOutParameter(5, java.sql.Types.INTEGER);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            // Se obtienen la salida del procedimineto almacenado
            existeU = cst.getInt(3);
            rol = cst.getString(4);
            idU = cst.getInt(5);
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        finally {
            cn.desconexion();
        }
        
        validadorUsuario.add(Integer.toString(existeU));
        validadorUsuario.add(rol);
        validadorUsuario.add(Integer.toString(idU));
        
        return validadorUsuario;
    }              
            
}
