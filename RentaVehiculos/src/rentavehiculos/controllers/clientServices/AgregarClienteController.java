/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.clientServices;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.alerts.GeneralAlert;
import rentavehiculos.classes.alerts.InfoAlert;
import rentavehiculos.classes.alerts.WarningAlert;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.classes.validaciones.Validaciones;
import rentavehiculos.entities.Cliente;

/**
 *
 * @author Uchiha ClanPc
 */
public class AgregarClienteController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField identificacionIC;
    
    @FXML
    private ComboBox<String> ruc_ci;
    
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
       if(Pruebas.getInstancia().getRentar() != null){
            Pruebas.getInstancia().getRentar().show();
            Pruebas.getInstancia().setRentar(null);
       }else{
           Pruebas.getInstancia().setFuncionalidad(null);
           Pruebas.getInstancia().getSubmenu().show();
       }
       stage.close();
    }
    
    @FXML
    void limpiar(MouseEvent event) throws IOException {
       this.identificacionIC.setText("");
       this.direccionIC.setText("");
       this.nombreIC.setText("");
       this.razonSocialIC.setText("");
       this.ruc_ci.getSelectionModel().selectFirst();
       this.telefonoIC.setText("");
    }
    @FXML
    void guardar(MouseEvent event){
        String identificacionG = identificacionIC.getText().trim();
        String tipoG= this.ruc_ci.getSelectionModel().getSelectedItem().toString();
        String nombreG=this.nombreIC.getText();
        String telefonoG=this.telefonoIC.getText();
        String direccionG =this.direccionIC.getText();
        String razonSocialG = this.razonSocialIC.getText();
        
        if(identificacionG.equals("") || nombreG.equals("") || telefonoG.equals("") ||
           direccionG.equals("") || razonSocialG.equals("")){
            this.mostrarInfoNoExito("Existen campos sin llenar por favor llene todos.");
        }else if(tipoG.equals("Persona") && !this.validarCedula(identificacionG)){
            this.mostrarInfoNoExito("El numero de cedula debe ser de 10 digitos numericos.");
        }else if (tipoG.equals("Corporación") && !this.validarRUC(identificacionG)){
            this.mostrarInfoNoExito("El numero de RUC debe ser de 13 digitos numericos.");
        }else if(!this.validarEnteroPositivo(telefonoG, -1, -1)){
            this.mostrarInfoNoExito("Telefono ingresado no valido,no debe contener letras.");
        }else{
            System.out.println("Guardando");
            CallableStatement cst=null;
            Conexion conn = new Conexion();
            try{
                cst = conn.getConnection().prepareCall("{call insertarCliente(?,?,?,?,?,?)}");
                if(tipoG.equalsIgnoreCase("Persona")){
                    cst.setInt(1, 0);
                }else{
                    cst.setInt(1, 1);
                }
                cst.setString(2, identificacionG);
                cst.setString(3, nombreG);
                cst.setString(4, telefonoG);
                cst.setString(5, direccionG);
                cst.setString(6, razonSocialG);

                cst.execute();
                this.mostrarInfoExito();
                if(Pruebas.getInstancia().getRentar() != null){
                    Stage stage = (Stage) identificacionIC.getScene().getWindow();
                    stage.close();
                    Pruebas.getInstancia().getRentar().show();
                    Pruebas.getInstancia().setRentar(null);
                }
                this.identificacionIC.setText("");
                this.direccionIC.setText("");
                this.nombreIC.setText("");
                this.razonSocialIC.setText("");
                this.ruc_ci.getSelectionModel().selectFirst();
                this.telefonoIC.setText("");
                
                
                //stage.close(); //Quitar Comentario para cerrar la ventana actual
                //Pruebas.getInstancia().mostrarAnyVentana("");  //menu principal
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
                this.mostrarInfoNoExito("Ya existe un registro con ese numero de cedula");
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
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ruc_ci.getItems().removeAll(ruc_ci.getItems());
        ruc_ci.getItems().addAll("Persona", "Corporación");
        ruc_ci.getSelectionModel().selectFirst();
        
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

        g.setMensaje("Cliente Agregado con exito");

        g.showAlert();
        
    }
    
    private boolean validarCedula(String cedula) {
        Matcher encajaCedula;
        encajaCedula = Validaciones.obtenerMatcher("[0-9]{10}", cedula);
        return encajaCedula.matches() || cedula.equals("");
    }
    
    private boolean validarRUC(String RUC) {
        Matcher encajaRUC;
        encajaRUC = Validaciones.obtenerMatcher("[0-9]{13}", RUC);
        return encajaRUC.matches() || RUC.equals("");
    }
    
    
    
}
