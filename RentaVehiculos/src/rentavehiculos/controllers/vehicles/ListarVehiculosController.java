/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.vehicles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.entities.Cliente;
import rentavehiculos.entities.Vehiculo;

/**
 *
 * @author Uchiha ClanPc
 */
public class ListarVehiculosController implements Initializable{
    
    private Stage app;
    
    ObservableList<Vehiculo> vehiculosOL;
    
    @FXML
    private TableView<Vehiculo> vehiculos;

    @FXML
    private TableColumn<Vehiculo,String> estado;

    @FXML
    private TableColumn<Vehiculo,String> matricula;

    @FXML
    void volver(MouseEvent event) {

    }

    @FXML
    void salir(MouseEvent event) {

    }

    @FXML
    void cargarLista(MouseEvent event) {
        vehiculosOL=Pruebas.getInstancia().getListaVehiculos();
        vehiculos.setItems(vehiculosOL);

    }

    @FXML
    void verVehiculo(MouseEvent event) throws IOException {
        Vehiculo vehicleToShow=vehiculos.getSelectionModel().getSelectedItem();
        Pruebas.getInstancia().setVehiculoAMostrar(vehicleToShow);
        Stage stage = (Stage) vehiculos.getScene().getWindow();
        //stage.close(); //Quitar Comentario para cerrar la ventana actual
        Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/vehicles/InfoVehiculo.fxml");
    }

    @FXML
    void modificarDisponibilidad(MouseEvent event) throws IOException {
        Vehiculo vehicleToShow=vehiculos.getSelectionModel().getSelectedItem();
        Pruebas.getInstancia().setVehiculoAMostrar(vehicleToShow);
        Stage stage = (Stage) vehiculos.getScene().getWindow();
        //stage.close(); //Quitar Comentario para cerrar la ventana actual
        Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/vehicles/ModificarDisponibilidadVehiculo.fxml");

    }
    
    @FXML
    void modificarVehiculo(MouseEvent event) throws IOException {
    }
    
    public void setApp(Stage app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        matricula.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("matricula"));
        estado.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("estado"));
    }
    
}
