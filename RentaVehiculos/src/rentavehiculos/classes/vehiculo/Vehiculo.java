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
    public static ResultSet getListadoMatriculas(Conexion cn) {
        try {
            CallableStatement cst = cn.getConnection().prepareCall("{call"
                    + " obtenerMatriculas()}");
            cst.execute(); 
            return cst.getResultSet();

        } catch (SQLException ex) {
            return null;   
        }
        
    }
    public static int[] getRangoAños(Conexion cn) {
        try {
            int[] años = new int[2];
            CallableStatement cst = cn.getConnection().prepareCall("{call"
                    + " obtenerRangoAños(?,?)}");
            cst.registerOutParameter(1, java.sql.Types.INTEGER);
            cst.registerOutParameter(2, java.sql.Types.INTEGER);
            cst.execute();
            años[0] = cst.getInt(1);
            años[1] = cst.getInt(2);
            return años;

        } catch (SQLException ex) {
            return null;   
        }
        
    }
    
    public static int[] getRangoCapacidad(Conexion cn) {
        try {
            int[] capacidad = new int[2];
            CallableStatement cst = cn.getConnection().prepareCall("{call"
                    + " obtenerRangoCapacidad(?,?)}");
            cst.registerOutParameter(1, java.sql.Types.INTEGER);
            cst.registerOutParameter(2, java.sql.Types.INTEGER);
            cst.execute();
            capacidad[0] = cst.getInt(1);
            capacidad[1] = cst.getInt(2);
            return capacidad;

        } catch (SQLException ex) {
            return null;   
        }
        
    }
    
    public static float[] getRangoPrecios(Conexion cn) {
        try {
            float[] precios = new float[2];
            CallableStatement cst = cn.getConnection().prepareCall("{call"
                    + " obtenerRangoPrecios(?,?)}");
            cst.registerOutParameter(1, java.sql.Types.FLOAT);
            cst.registerOutParameter(2, java.sql.Types.FLOAT);
            cst.execute();
            precios[0] = cst.getFloat(1);
            precios[1] = cst.getFloat(2);
            return precios;

        } catch (SQLException ex) {
            return null;   
        }
        
    }
}
 