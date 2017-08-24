package rentavehiculos.controllers.proveedor;

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
public class InsertarProveedorController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField nombre;
    
    @FXML
    private TextField direccion; 
    
    @FXML
    private TextField pais;

    @FXML
    private TextField ciudad;
    
    @FXML
    private TextField telefono; 
    
    @FXML
    private TextField e_mail;
    
    
    
    
    public void setApp(Stage app) {
        this.app = app;
    }
    
    @FXML
    public void atras(MouseEvent event) throws IOException{
        Stage stage = (Stage) telefono.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        //Pruebas.getInstancia().mostrarAnyVentana("");
        
    }
    
    
    @FXML
    public void agregar(MouseEvent Event) throws IOException, SQLException{
        
        Conexion conn = new Conexion();
        String nombreG=nombre.getText();
	String direccionG=direccion.getText();
        String paisG=pais.getText();
	String ciudadG=ciudad.getText();
	String telefonoG=telefono.getText();
	String e_mailG=e_mail.getText();

        
        try{
            cst = conn.getConnection().
                    prepareCall("{call  insertarCliente(?,?,?,?,?,?)}");

            if(!nombreG.equals("")){
                    cst.setString(1, nombreG);
            }else{
               cst.setNull(1,java.sql.Types.VARCHAR);
            }
	    
	    if(!direccionG.equals("")){
                    cst.setString(2, direccionG);
            }else{
               cst.setNull(2,java.sql.Types.VARCHAR);
            }
            
	    if(!paisG.equals("")){
                    cst.setString(3, paisG);
            }else{
               cst.setNull(3,java.sql.Types.VARCHAR);
            }

	    if(!ciudadG.equals("")){
                    cst.setString(4, ciudadG);
            }else{
               cst.setNull(4,java.sql.Types.VARCHAR);
            }

	    if(!telefonoG.equals("")){
                    cst.setString(5, telefonoG);
            }else{
               cst.setNull(5,java.sql.Types.VARCHAR);
            }

	    if(!e_mailG.equals("")){
                    cst.setString(6, e_mailG);
            }else{
               cst.setNull(6,java.sql.Types.VARCHAR);
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
        direccion.setText("");
        pais.setText("");
        ciudad.setText("");
        telefono.setText("");
        e_mail.setText("");


    }
    
    
}
