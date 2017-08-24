package rentavehiculos.controllers.empleados;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.entities.Cliente;

/**
 *
 * @author Uchiha ClanPc
 */
public class InsertarCuidadoController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField cedula;
    
    @FXML
    private TextField vehiculo; //placa
    
    @FXML
    private TextField observaciones;
    
    
    
    
    public void setApp(Stage app) {
        this.app = app;
    }
    
    @FXML
    public void atras(MouseEvent event) throws IOException{
        Stage stage = (Stage) cedula.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        //Pruebas.getInstancia().mostrarAnyVentana("");
        
    }
    
    
    @FXML
    public void agregar(MouseEvent Event) throws IOException, SQLException{
        
        Conexion conn = new Conexion();
        String cedulaG=cedula.getText();
	String vehiculoG=vehiculo.getText();
	String observacionesG=observaciones.getText();
	CallableStatement cst=null;

        
        try{
            cst = conn.getConnection().
                    prepareCall("{call  insertarCuidado(?,?,?)}");

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
            
	    if(!observacionesG.equals("")){
                    cst.setString(3, observacionesG);
            }else{
               cst.setNull(3,java.sql.Types.VARCHAR);
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
        observaciones.setText("");


    }
    
    
}
