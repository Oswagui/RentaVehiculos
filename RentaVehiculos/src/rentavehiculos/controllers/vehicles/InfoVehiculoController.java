/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.vehicles;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import rentavehiculos.Pruebas;
import rentavehiculos.entities.Vehiculo;

/**
 *
 * @author Uchiha ClanPc
 */
public class InfoVehiculoController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField marca;

    @FXML
    private TextField tipo;

    @FXML
    private TextField disponibilidad;

    @FXML
    private TextField precio;

    @FXML
    private TextField color;

    @FXML
    private TextField matricula;

    @FXML
    private ImageView imagen;

    @FXML
    private TextField proveedor;

    @FXML
    private TextField modelo;

    @FXML
    private TextField año;

    @FXML
    private TextField capacidad;
    
    public void setApp(Stage app) {
        this.app = app;
    }

    @FXML
    void atras(MouseEvent event) throws IOException {
        Stage stage = (Stage) matricula.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/vehicles/ListarVehiculos.fxml");
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vehiculo vehicleToShow=Pruebas.getInstancia().getVehiculoAMostrar();
        matricula.setText(vehicleToShow.getMatricula());
        proveedor.setText(vehicleToShow.getProveedor().getNombre());
        tipo.setText(vehicleToShow.getTipo());
        marca.setText(vehicleToShow.getMarca());
        año.setText(vehicleToShow.getAño().toString());
        modelo.setText(vehicleToShow.getNombreModelo());
        disponibilidad.setText(vehicleToShow.getEstado());
        color.setText(vehicleToShow.getColor());
        capacidad.setText(vehicleToShow.getCapacidad().toString());
        precio.setText(vehicleToShow.getPrecio().toString());
        
        Image fotoVehiculo=new Image(new ByteArrayInputStream(vehicleToShow.getFoto()));
        imagen.setImage(fotoVehiculo);
    }
    
    
    
}
