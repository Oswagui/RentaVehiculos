package rentavehiculos.controllers.empleados;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.classes.connection.Conexion;

/**
 *
 * @author Uchiha ClanPc
 */
public class InsertarReparacionController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField cedula;
    
    @FXML
    private TextField vehiculo; //placa

    @FXML
    private TextField costo;

    @FXML
    private TextField fecha;
    
    @FXML
    private TextField descripcion;
    
    
    
    
    public void setApp(Stage app) {
        this.app = app;
    }
    
    @FXML
    public void atras(MouseEvent event) throws IOException{
        Stage stage = (Stage) costo.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        //Pruebas.getInstancia().mostrarAnyVentana("");
        
    }
    
    
    @FXML
    public void agregar(MouseEvent Event) throws IOException, SQLException{
        
        Conexion conn = new Conexion();
        String cedulaG=cedula.getText();
	String vehiculoG=vehiculo.getText();
	String costoG=costo.getText();
	String fechaG=fecha.getText();
	String descripcionG=descripcion.getText();
	CallableStatement cst=null;

        
        try{
            cst = conn.getConnection().
                    prepareCall("{call  insertarReparacion(?,?,?)}");

            if(!cedulaG.equals("")){
                    cst.setString(1, cedulaG);
            }else{
               cst.setNull(1,java.sql.Types.VARCHAR);
            }
	    
	    if(!vehiculoG.equals("")){
                    cst.setString(2, vehiculoG);
            }else{
               cst.setNull(2,java.sql.Types.VARCHAR);
            }

	    if(!costoG.equals("")){
                    cst.setFloat(3, Float.parseFloat(costoG));
            }else{
               cst.setNull(3,java.sql.Types.FLOAT);
            }
	    if(!fechaG.equals("")){
                try {
                    //validar fecha formato dd/mm/yyyy
                    cst.setTimestamp(4, stringToTimestamp(fechaG));
                } catch (ParseException ex) {
                    System.out.println("Error al parseo de fecha");
                }
            }else{
                    cst.setNull(4,java.sql.Types.TIMESTAMP);
            }
	    
            
	    if(!descripcionG.equals("")){
                    cst.setString(5, descripcionG);
            }else{
               cst.setNull(5,java.sql.Types.VARCHAR);
            }
            
            cst.execute();                   
            
            vaciarCampos();
            
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        finally {
            try {
                conn.desconexion();
                if(cst!=null){
                   cst.close();
                }
            }
            catch(SQLException e){
                    
            }
            
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }
    
    @FXML
    public void limpiar(MouseEvent Event) throws IOException{
        vaciarCampos();
    }
    
    public void vaciarCampos(){ 
        
        cedula.setText("");
        vehiculo.setText("");
	costo.setText("");
	fecha.setText("");
        //observaciones.setText("");


    }

    public static Timestamp stringToTimestamp(String fecha) throws ParseException {
    
      DateFormat formatoFecha;
      formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
      Date date = formatoFecha.parse(fecha);
      java.sql.Timestamp fechaTS = new Timestamp(date.getTime());
      return fechaTS;
      
    }
    
    
}
