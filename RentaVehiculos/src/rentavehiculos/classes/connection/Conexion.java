package rentavehiculos.classes.connection;



import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
//import simacom.classes.constants.AlertsSystem;

public class Conexion {

    private String _usuario = "root";
    private String _pwd = "harold";
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String DATABASE = "AlquilerDeVehiculos";
    static String _url = "jdbc:mysql://localhost:3306/" + DATABASE;
    private Connection conn = null;

    public Conexion() {
        try {
            Class.forName(CONTROLADOR);
            conn = (Connection)DriverManager.getConnection(_url, _usuario, _pwd);
            if(conn != null) {
                System.out.println("Conexion a base de datos "+_url+" Exitosa");
            }
        } catch(SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexión");
//           AlertsSystem.showWarning(1);
        }
        //System.out.println(ex);
  
    }

    public ResultSet getQuery(String _query) {
        Statement state;
        ResultSet resultado = null;
        try {
            state = (Statement) conn.createStatement();
            resultado = state.executeQuery(_query);
        } catch(SQLException e) {
//            AlertsSystem.showWarning(1);
            //e.printStackTrace();
        }
        return resultado;
    }
	 
    public void setQuery(String _query){

        Statement state;

        try {   
            state=(Statement) conn.createStatement();
            state.execute(_query);
        } catch (SQLException e) {
//            AlertsSystem.showWarning(1);
            //e.printStackTrace();
        }
    }

    public java.sql.Connection getConnection() {
        return this.conn;
    }
    
    public void desconexion(){
        this.conn=null;
        if (conn==null){
            System.out.println("Se ha desconectado de la base de datos...");
        }
    }

}
