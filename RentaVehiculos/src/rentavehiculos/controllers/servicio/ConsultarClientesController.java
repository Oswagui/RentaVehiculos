/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.servicio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 *
 * @author Harold
 */
public class ConsultarClientesController implements Initializable{
   //private Cliente client;
    
    private Stage app;
    
    @FXML
    private TextField identificacion;
    
    @FXML
    private ComboBox tipo;
     
    @FXML
    private TextField rDesde;
    
    @FXML
    private TextField rHasta;
    
    @FXML
    private TextField nombre;
    
    @FXML
    private TextField rSocial;
    
    @FXML
    private TextField dgDesde;
    
    @FXML
    private TextField dgHasta;
    
     public void setApp(Stage app) {
        this.app = app;
    }
    
    @FXML
    private void buscar(MouseEvent Event){
        
    }
     
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
