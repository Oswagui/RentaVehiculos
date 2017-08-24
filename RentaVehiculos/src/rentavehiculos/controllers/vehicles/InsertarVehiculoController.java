/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.vehicles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
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
    public void atras(MouseEvent event) throws IOException{
        Stage stage = (Stage) direccionIC.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        Pruebas.getInstancia().mostrarNuevaVentana();
        
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
    
}
