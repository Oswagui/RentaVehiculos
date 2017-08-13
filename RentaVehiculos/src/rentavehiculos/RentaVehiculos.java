/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import rentavehiculos.creators.LoginCreator;

/**
 *
 * @author Oswaldo
 */
public class RentaVehiculos extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage = LoginCreator.loginCreator();
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
