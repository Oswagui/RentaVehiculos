/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.classes.vehiculo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rentavehiculos.classes.connection.Conexion;

/**
 *
 * @author Oswaldo
 */
public class Vehiculo {
    public static ResultSet getListadoColores(Conexion cn) {
        try {
            CallableStatement cst = cn.getConnection().prepareCall("{call"
                    + " obtenerColores()}");
            cst.execute(); 
            return cst.getResultSet();

        } catch (SQLException ex) {
            return null;   
        }
        
    }
    
    public static ResultSet getListadoTipos(Conexion cn) {
        try {
            CallableStatement cst = cn.getConnection().prepareCall("{call"
                    + " obtenerTipos()}");
            cst.execute(); 
            return cst.getResultSet();

        } catch (SQLException ex) {
            return null;   
        }
        
    }
}
