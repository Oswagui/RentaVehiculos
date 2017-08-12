/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos;

import javafx.application.Application;
import javafx.stage.Stage;
import rentavehiculos.creators.ConsultarClienteCreator;
import rentavehiculos.creators.LoginCreator;

/**
 *
 * @author Osw
 */
public class Pruebas extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage = ConsultarClienteCreator.consultarCliente();
        stage.setTitle("RentaVehiculo 2.0"); 
        stage.show();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
    
