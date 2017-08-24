package rentavehiculos.controllers.departamentos;

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
public class InsertarDepartamentoController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField nombre;
    
    
    public void setApp(Stage app) {
        this.app = app;
    }
    
    @FXML
    public void atras(MouseEvent event) throws IOException{
        Stage stage = (Stage) precio.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        //Pruebas.getInstancia().mostrarAnyVentana("");
        
    }
    
    
    @FXML
    public void agregar(MouseEvent Event) throws IOException, SQLException{
        
        Conexion conn = new Conexion();
        String nombreG=nombre.getText();

        
        try{
            cst = conn.getConnection().
                    prepareCall("{call  insertarCliente(?)}");

            if(!nombreG.equals("")){
                    cst.setString(1, nombreG);
            }else{
               cst.setNull(1,java.sql.Types.VARCHAR);
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
        
        nombre.setText("");


    }
    
    
}
