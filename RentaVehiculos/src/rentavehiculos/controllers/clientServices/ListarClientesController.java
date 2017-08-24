/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.clientServices;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.entities.Cliente;

/**
 *
 * @author Harold
 */
public class ListarClientesController implements Initializable{
    
    private Stage app;
    
    @FXML
    TableView<Cliente> clientes;
    
    @FXML   
    TableColumn<Cliente,String> identificacionLC;
    
    @FXML
    TableColumn<Cliente,String> tipoLC;
    
    ObservableList<Cliente> clientesOL;
    
    
    public ListarClientesController(){
        
    }
    
    public void setApp(Stage app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identificacionLC.setCellValueFactory(new PropertyValueFactory<Cliente,String>("identificacion"));
        tipoLC.setCellValueFactory(new PropertyValueFactory<Cliente,String>("tipo"));
        
    }
    
    @FXML
    private void cargarLista(MouseEvent Event){
        clientesOL=Pruebas.getInstancia().getListaClientes();
        clientes.setItems(clientesOL);     
    }
    
    @FXML
    private void verCliente(MouseEvent Event) throws IOException{
        Cliente clientToShow=clientes.getSelectionModel().getSelectedItem();
        Pruebas.getInstancia().setClienteAMostrar(clientToShow);
        cerrarVentana();
        Pruebas.getInstancia().mostrarNuevaVentana2();
    }
    
    @FXML
    private void volver(MouseEvent Event) throws IOException{
        cerrarVentana();
        Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/vehicles/ConsultarVehiculo.fxml");
    }
    
    @FXML
    private void salir(MouseEvent Event){
        cerrarVentana();
    }
    
    public void cerrarVentana(){
        Stage stage = (Stage) clientes.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual      
    }
}
