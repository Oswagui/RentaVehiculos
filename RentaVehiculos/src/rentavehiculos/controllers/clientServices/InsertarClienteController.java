/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.clientServices;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.creators.AnyCreator;
import rentavehiculos.entities.Cliente;

/**
 *
 * @author Uchiha ClanPc
 */
public class InsertarClienteController {
    private Stage app;
    
    @FXML
    private TextField id_cliente;
    
    @FXML
    private ComboBox<String> ruc_ci;
    
    @FXML
    private TextField identificacion;
    
    @FXML
    private TextField nombre;
    
    @FXML
    private TextField telefono;
         
    @FXML
    private TextField direccion;
    
    @FXML
    private TextField rSocial;
    
    public void setApp(Stage app) {
        this.app = app;
    }  
    
    @FXML
    public void atras(MouseEvent Event) throws IOException{
        Stage stage = (Stage) direccion.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/clientServices/submenuAtencion.fxml");
    }
    
    @FXML
    public void agregar(MouseEvent Event) throws IOException, SQLException{
        
        Conexion conn = new Conexion();
        String id_clienteG=id_cliente.getText();
        String rucCiG=ruc_ci.getSelectionModel().getSelectedItem().toString();
        String identificacionG=identificacion.getText();
        String nombreG=nombre.getText();
        String telefonoG=telefono.getText();
        String direccionG=direccion.getText();
        String rSocialG=rSocial.getText();  
        CallableStatement cst=null;
        
        try{
            cst = conn.getConnection().
                    prepareCall("{call  insertarCliente(?,?,?,?,?,?,?)}");
            
            if(!id_clienteG.equals("")){
                cst.setInt(1, Integer.parseInt(id_clienteG));
            }else{
                cst.setInt(1,Integer.parseInt(identificacionG)-935913);
            }
            
            if(rucCiG.equals("Corporación")){
               cst.setBoolean(2,Boolean.TRUE);
            }else if(rucCiG.equals("Persona")){
               cst.setBoolean(2,Boolean.FALSE);
            }else{
               cst.setNull(2,java.sql.Types.TINYINT);
            }
            
            if(!identificacionG.equals("")){
                cst.setString(3, identificacionG);
            }else{
                cst.setNull(3,java.sql.Types.VARCHAR);
            }
            
            if(!nombreG.equals("")){
                cst.setString(4, nombreG);
            }else{
                cst.setNull(4,java.sql.Types.VARCHAR);
            }
            
            if(!telefonoG.equals("")){
                cst.setString(5, telefonoG);
            }else{
                cst.setNull(5,java.sql.Types.VARCHAR);
            }
            
            if(!direccionG.equals("")){
                cst.setString(6, nombreG);
            }else{
                cst.setNull(6,java.sql.Types.VARCHAR);
            }
            
            if(!rSocialG.equals("")){
                cst.setString(7, rSocialG);
            }else{
                cst.setNull(7,java.sql.Types.VARCHAR);
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
    
    @FXML
    public void limpiar(MouseEvent Event) throws IOException{
        vaciarCampos();
    }
    
    public void vaciarCampos(){
        id_cliente.setText("");
        ruc_ci.getSelectionModel().select("");
        identificacion.setText("");
        nombre.setText("");
        telefono.setText("");
        direccion.setText("");
        rSocial.setText("");

    }
}
