/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.adminServices;

import rentavehiculos.controllers.clientServices.*;
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
import javafx.scene.control.ComboBox;
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
import rentavehiculos.Pruebas;
import rentavehiculos.creators.AnyCreator;

/**
 *
 * @author Oswaldo
 */
public class SubmenuAdminController implements Initializable {
    
    @FXML
    private Pane fondo;
    
    @FXML
    private Button ingresos;
    
    @FXML
    private Button modificaciones;
    
    @FXML
    private Button eliminaciones;
    
    @FXML
    private Button cerrarSesion;
    
    @FXML
    private ComboBox<String> opcion;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.opcion.getItems().addAll("Cliente", "Vehiculo", "Empleado");
        this.opcion.getSelectionModel().selectFirst();
        Image fondoPane = new Image(new File("src/rentavehiculos/util/images/fondoAdmin.jpg").toURI().toString());
        this.fondo.setBackground(new Background(new BackgroundImage(fondoPane, BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        
        Image boton = new Image(new File("src/rentavehiculos/util/images/botonIngresos.png").toURI().toString());
        this.ingresos.setBackground(new Background(new BackgroundImage(boton, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.ingresos.setMaxSize(350, 176);
        this.ingresos.setPrefSize(350, 176);
        this.ingresos.setLayoutX(50);
        this.ingresos.setLayoutY(520);
        this.ingresos.setOnMouseEntered(new estimular(this.ingresos, true));
        this.ingresos.setOnMouseExited(new estimular(this.ingresos, false));
        this.ingresos.setVisible(true);
        
        boton = new Image(new File("src/rentavehiculos/util/images/bodonModificar.png").toURI().toString());
      
        this.modificaciones.setBackground(new Background(new BackgroundImage(boton, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.modificaciones.setMaxSize(350, 176);
        this.modificaciones.setPrefSize(350, 176);
        this.modificaciones.setLayoutX(850);
        this.modificaciones.setLayoutY(520);
        this.modificaciones.setOnMouseEntered(new estimular(this.modificaciones, true));
        this.modificaciones.setOnMouseExited(new estimular(this.modificaciones, false));
        
        boton = new Image(new File("src/rentavehiculos/util/images/bodonEliminar.png").toURI().toString());
      
        this.eliminaciones.setBackground(new Background(new BackgroundImage(boton, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.eliminaciones.setMaxSize(350, 176);
        this.eliminaciones.setPrefSize(350, 176);
        this.eliminaciones.setLayoutX(450);
        this.eliminaciones.setLayoutY(520);        
        this.eliminaciones.setOnMouseEntered(new estimular(this.eliminaciones, true));
        this.eliminaciones.setOnMouseExited(new estimular(this.eliminaciones, false));
        
        
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
       Pruebas.getInstancia().setSubmenu(null);
       Pruebas.getInstancia().getLogin().show();
       stageSubmenu.close();
       
    }
    
    public void ingresos(){
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
        
        Pruebas.getInstancia().getSubmenu().hide();
        ventana.setResizable(false);
        ventana.setMaximized(false);
        Pruebas.getInstancia().setFuncionalidad(ventana);
        ventana.show();
        
    }

    public void modificaciones(){
        Stage ventana = null;
        try {
            ventana = AnyCreator.anyCreator("src/rentavehiculos/screens/clientServices/ConsultarCliente.fxml");
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
        
        Pruebas.getInstancia().getSubmenu().hide();
        ventana.setResizable(false);
        ventana.setMaximized(false);
        Pruebas.getInstancia().setFuncionalidad(ventana);
        ventana.show();
    }
    
    public void eliminaciones(){
        Stage ventana = null;
        try {
            ventana = AnyCreator.anyCreator("src/rentavehiculos/screens/clientServices/AgregarCliente.fxml");
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
        
        Pruebas.getInstancia().getSubmenu().hide();
        ventana.setResizable(false);
        ventana.setMaximized(false);
        Pruebas.getInstancia().setFuncionalidad(ventana);
        ventana.show();
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
