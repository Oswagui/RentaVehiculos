/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.clientServices;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import rentavehiculos.creators.AnyCreator;

/**
 *
 * @author Oswaldo
 */
public class SubmenuAtencionController implements Initializable {
    
    @FXML
    private Pane fondo;
    
    @FXML
    private Button agregarCliente;
    
    @FXML
    private Button consultarVehiculo;
    
    @FXML
    private Button alquilarVehiculo;
    
    @FXML
    private Button consultarCliente;
    
    @FXML
    private Button cerrarSesion;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        Image fondoPane = new Image(new File("src/rentavehiculos/util/images/fondo2.jpg").toURI().toString());
        this.fondo.setBackground(new Background(new BackgroundImage(fondoPane, BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        
        Image boton = new Image(new File("src/rentavehiculos/util/images/bontonAlquilar.png").toURI().toString());
        this.alquilarVehiculo.setBackground(new Background(new BackgroundImage(boton, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.alquilarVehiculo.setMaxSize(350, 176);
        this.alquilarVehiculo.setPrefSize(350, 176);
        this.alquilarVehiculo.setLayoutX(850);
        this.alquilarVehiculo.setLayoutY(300);
        this.alquilarVehiculo.setOnMouseEntered(new estimular(this.alquilarVehiculo, true));
        this.alquilarVehiculo.setOnMouseExited(new estimular(this.alquilarVehiculo, false));
        
        boton = new Image(new File("src/rentavehiculos/util/images/bontonAgregarCLiente.png").toURI().toString());
      
        this.agregarCliente.setBackground(new Background(new BackgroundImage(boton, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.agregarCliente.setMaxSize(350, 176);
        this.agregarCliente.setPrefSize(350, 176);
        this.agregarCliente.setLayoutX(850);
        this.agregarCliente.setLayoutY(520);
        this.agregarCliente.setOnMouseEntered(new estimular(this.agregarCliente, true));
        this.agregarCliente.setOnMouseExited(new estimular(this.agregarCliente, false));
        
        boton = new Image(new File("src/rentavehiculos/util/images/bontonConsultaCliente.png").toURI().toString());
      
        this.consultarCliente.setBackground(new Background(new BackgroundImage(boton, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.consultarCliente.setMaxSize(350, 176);
        this.consultarCliente.setPrefSize(350, 176);
        this.consultarCliente.setLayoutX(450);
        this.consultarCliente.setLayoutY(520);        
        this.consultarCliente.setOnMouseEntered(new estimular(this.consultarCliente, true));
        this.consultarCliente.setOnMouseExited(new estimular(this.consultarCliente, false));
        
        boton = new Image(new File("src/rentavehiculos/util/images/botonConsultarVehiculo.png").toURI().toString());
      
        this.consultarVehiculo.setBackground(new Background(new BackgroundImage(boton, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.consultarVehiculo.setMaxSize(350, 176);
        this.consultarVehiculo.setPrefSize(350, 176);
        this.consultarVehiculo.setLayoutX(50);
        this.consultarVehiculo.setLayoutY(520);        
        this.consultarVehiculo.setOnMouseEntered(new estimular(this.consultarVehiculo, true));
        this.consultarVehiculo.setOnMouseExited(new estimular(this.consultarVehiculo, false));
        
        boton = new Image(new File("src/rentavehiculos/util/images/cerrarSesion.png").toURI().toString());
      
        this.cerrarSesion.setBackground(new Background(new BackgroundImage(boton, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.cerrarSesion.setMaxSize(125, 125);
        this.cerrarSesion.setPrefSize(125, 125);
        this.cerrarSesion.setLayoutX(970);
        this.cerrarSesion.setLayoutY(130);
        this.cerrarSesion.setOnMouseEntered(new estimular(this.cerrarSesion, true));
        this.cerrarSesion.setOnMouseExited(new estimular(this.cerrarSesion, false));
    }
    
    public void cerrarSesion(){
       Stage stageSubmenu = (Stage)((Node)this.fondo).getScene().getWindow();
       stageSubmenu.close();
    }
    
    public void consultarVehiculo(){
        Stage ventana = null;
        try {
            ventana = AnyCreator.anyCreator("src/rentavehiculos/screens/vehicles/ConsultarVehiculo.fxml");
        } catch (IOException ex) {
            System.out.println("Error");
        }
        Stage stageSubmenu = (Stage)((Node)this.fondo).getScene().getWindow();
        stageSubmenu.hide();
        ventana.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("No puede cerrar la ventana asi");
                event.consume();}
        });
        ventana.setResizable(false);
        ventana.setMaximized(false);
        ventana.showAndWait();
        stageSubmenu.show();
    }
    private class estimular implements EventHandler<MouseEvent> {
        Button btn;
        boolean estimulo;

        public estimular(Button btn, boolean estimulo) {
            this.btn = btn;
            this.estimulo = estimulo;
        }
        
        @Override
        public void handle(MouseEvent event) {
            DropShadow shadow = new DropShadow();
            shadow.setColor(Color.CORAL);
            shadow.setRadius(20);
            if (estimulo) {
                btn.setEffect(shadow);
            }
            else {
                btn.setEffect(null);
            }
        }
    }
    
}
