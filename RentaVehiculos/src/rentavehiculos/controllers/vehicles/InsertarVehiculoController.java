/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.vehicles;

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
public class InsertarVehiculoController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField matricula;
    
    @FXML
    private TextField proveedor;
    
    @FXML
    private TextField tipo;
    
    @FXML
    private TextField marca;
    
    @FXML
    private TextField año;
    
    @FXML
    private TextField nombre_modelo;
    
    @FXML
    private TextField color;
    
    @FXML
    private TextField precio;
    
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
        String matriculaG=matricula.getText();
        String NombreProveedorG=proveedor.getText();
        String tipoG=tipo.getText();
        String marcaG=marca.getText();
        String añoG=año.getText();
        String modeloG=nombre_modelo.getText();  
        String colorG=color.getText();
        String precioG=precio.getText();
        CallableStatement cst=null;
        
        try{
            cst = conn.getConnection().
                    prepareCall("{call  insertarVehiculo(?,?,?,?,?,?,?,?)}");

            if(!matriculaG.equals("")){
                    cst.setString(1, matriculaG);
            }else{
               cst.setNull(1,java.sql.Types.VARCHAR);
            }
            
            if(!NombreProveedorG.equals("")){
                cst.setString(2, NombreProveedorG);
            }else{
                cst.setNull(2,java.sql.Types.VARCHAR);
            }
            
            if(!tipoG.equals("")){
                cst.setString(3, tipoG);
            }else{
                cst.setNull(3,java.sql.Types.VARCHAR);
            }
            
            if(!marcaG.equals("")){
                cst.setString(4, marcaG);
            }else{
                cst.setNull(4,java.sql.Types.VARCHAR);
            }
            
            if(!añoG.equals("")){
                cst.setInt(5, Integer.parseInt(añoG));
            }else{
                cst.setNull(5,java.sql.Types.INTEGER);
            }
            
            if(!modeloG.equals("")){
                cst.setString(6, modeloG);
            }else{
                cst.setNull(6,java.sql.Types.VARCHAR);
            }
            if(!colorG.equals("")){
                cst.setString(7, colorG);
            }else{
                cst.setNull(7,java.sql.Types.VARCHAR);
            }
            if(!precioG.equals("")){
                cst.setFloat(8, Float.parseFloat(precioG));
            }else{
                cst.setNull(8,java.sql.Types.FLOAT);
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
        
        matricula.setText("");
        proveedor.setText("");
        tipo.setText("");
        marca.setText("");
        año.setText("");
        nombre_modelo.setText("");  
        color.setText("");
        precio.setText("");

    }
    
    
}
