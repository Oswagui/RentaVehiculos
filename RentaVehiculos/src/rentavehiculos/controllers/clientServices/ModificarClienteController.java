/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.clientServices;

import rentavehiculos.controllers.vehicles.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.alerts.GeneralAlert;
import rentavehiculos.classes.alerts.InfoAlert;
import rentavehiculos.classes.alerts.WarningAlert;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.classes.validaciones.Validaciones;
import rentavehiculos.entities.Cliente;
import rentavehiculos.entities.Vehiculo;

/**
 *
 * @author Uchiha ClanPc
 */
public class ModificarClienteController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField identificacionIC;
    
    @FXML
    private TextField tipoIC;
    
    @FXML
    private TextField nombreIC;
    
    @FXML
    private TextField telefonoIC;
    
    @FXML
    private TextField direccionIC;
    
    @FXML
    private TextField razonSocialIC;
    
    public void setApp(Stage app) {
        this.app = app;
    }

    @FXML
    void atras(MouseEvent event) throws IOException {
        Stage stage = (Stage) identificacionIC.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        Pruebas.getInstancia().getSubmenu().show();
    }
    
    
    @FXML
    void guardar(MouseEvent event){
        String identificacionG = identificacionIC.getText().trim();
        String tipoG= this.tipoIC.getText();
        String nombreG=this.nombreIC.getText();
        String telefonoG=this.telefonoIC.getText();
        String direccionG =this.direccionIC.getText();
        String razonSocialG = this.razonSocialIC.getText();
        
        if(identificacionG.equals("") || tipoG.equals("") || nombreG.equals("")
           || telefonoG.equals("") || direccionG.equals("") || razonSocialG.equals("")){
            this.mostrarInfoNoExito("Campos vacios");
        }else if(!this.validarEnteroPositivo(telefonoG, -1, -1)){
            this.mostrarInfoNoExito("Telefono ingresado no valido,no debe contener letras.");
        }else{
            System.out.println("Guardando");
            CallableStatement cst=null;
            Conexion conn = new Conexion();
            try{
                
                cst = conn.getConnection().prepareCall("{call modificarCliente(?,?,?,?,?)}");
                cst.setString(1,identificacionG);
                cst.setString(2, nombreG);
                cst.setString(3, telefonoG);
                cst.setString(4,direccionG);
                cst.setString(5, razonSocialG);
                
               
                cst.execute();
                
                this.mostrarInfoExito();
                
                
                //stage.close(); //Quitar Comentario para cerrar la ventana actual
                //Pruebas.getInstancia().mostrarAnyVentana("");  //menu principal
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
                this.mostrarInfoNoExito("Np se puedo actualizar");
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
            //Programa Funcionalidad de Guardar
        
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cliente clientToShow=Pruebas.getInstancia().getClienteAMostrar();
        identificacionIC.setText(clientToShow.getIdentificacion());
        tipoIC.setText(clientToShow.getTipo());
        nombreIC.setText(clientToShow.getNombre());
        telefonoIC.setText(clientToShow.getTelefono());
        direccionIC.setText(clientToShow.getDireccion());
        razonSocialIC.setText(clientToShow.getRazonSocial());
        
    }
    
    private boolean validarEnteroPositivo(String numero, int inf, int sup) {
        Matcher encajaEnteroPositivo;
        encajaEnteroPositivo = Validaciones.obtenerMatcher("[0-9]*", numero);
        if(!encajaEnteroPositivo.matches())
            return false;
        if(inf == -1 && sup == -1)
            return true;
        return Integer.parseInt(numero) <= sup && Integer.parseInt(numero) >= inf;
    }
    
    private boolean validarDecimalPositivo(String precio, float inf, float sup) {
        Matcher encajaDecimalPositivo;
        encajaDecimalPositivo = Validaciones.obtenerMatcher("[0-9]*[.]{0,1}[0-9]*", precio);
        if(!encajaDecimalPositivo.matches())
            return false;
        if(inf == -1 && sup == -1)
            return true;
        return Float.parseFloat(precio) >= inf && Float.parseFloat(precio) <= sup;
    }
    
    private void mostrarInfoNoExito(String info) {
        
        GeneralAlert g;
        
        g = new WarningAlert();
        
        g.setMensaje(info);
        
        g.showAlert();
        
    }
    private void mostrarInfoExito() {
        
        GeneralAlert g;
                
        g = new InfoAlert();

        g.setMensaje("Valores Actualizados con exito");

        g.showAlert();
        
    }
    
    
}
