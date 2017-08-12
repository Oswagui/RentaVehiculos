/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.clientServices;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Osw
 */
public class ConsultarClienteController {
    
    @FXML
    private TextField cedula;
    
    @FXML
    private ComboBox tipoCliente;
    
    @FXML
    private TextField inferior;
    
    @FXML
    private TextField superior;
   
    @FXML
    private TextField NombreCliente;
    
    @FXML
    private CheckBox activa;
    
    @FXML
    private CheckBox devuelta;
    
    @FXML
    private CheckBox atraso;
    
    @FXML
    private TextField gastoInf;
    
    @FXML
    private TextField gastoSup;
     
    @FXML
    private void volver(MouseEvent event) {
        
    }
    
    @FXML
    private void buscar(MouseEvent event) {
    }
    
    @FXML
    private void limpiar(MouseEvent event) {
    }
}
