/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.creators;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mercedes
 */
public class ConsultarClienteCreator {
    public static Stage consultarCliente() throws IOException{
        
        Stage stage;
        
        Parent root;
        
        stage = new Stage();
        
        File archivo = new File("src/rentavehiculos/screens/clientServices/ConsultarCliente.fxml");
        
        URL url;
        
        url = archivo.toURI().toURL();
        
        root = FXMLLoader.load(url);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        return stage;


    }
}
