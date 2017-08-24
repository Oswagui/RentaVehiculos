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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.alerts.GeneralAlert;
import rentavehiculos.classes.alerts.WarningAlert;
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
    
    @FXML
    private Button showInfoButton;
    
    @FXML
    private Button modifyButton;
    
    public ListarClientesController(){
        
    }
    
    public void setApp(Stage app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identificacionLC.setCellValueFactory(new PropertyValueFactory<Cliente,String>("identificacion"));
        tipoLC.setCellValueFactory(new PropertyValueFactory<Cliente,String>("tipo"));
        this.showInfoButton.setDisable(true);
        this.modifyButton.setDisable(true);
        
    }
    
    @FXML
    private void cargarLista(MouseEvent Event){
        clientesOL=Pruebas.getInstancia().getListaClientes();
        clientes.setItems(clientesOL);
        this.showInfoButton.setDisable(false);
        this.modifyButton.setDisable(false);
    }
    
    @FXML
    private void verCliente(MouseEvent Event) throws IOException{
        Cliente clientToShow=clientes.getSelectionModel().getSelectedItem();
        if (clientToShow == null){
            this.mostrarInfoNoExito("No ha seleccionado cliente");
            return;
        }
        Pruebas.getInstancia().setClienteAMostrar(clientToShow);
        Stage stage = (Stage) clientes.getScene().getWindow();
        stage.close(); 
        Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/clientServices/InfoCliente.fxml");
    
    }
    
    @FXML
    void volver(MouseEvent event) throws IOException {
        cerrarVentana();
        Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/clientServices/ConsultarCliente.fxml");

    }

    @FXML
    void salir(MouseEvent event) {
        cerrarVentana();
        Pruebas.getInstancia().setFuncionalidad(null);
        Pruebas.getInstancia().getSubmenu().show();

    }
    
    @FXML
    void modificarCliente(MouseEvent event) throws IOException {
        Cliente clienteToShow=clientes.getSelectionModel().getSelectedItem();
        if (clienteToShow == null){
            this.mostrarInfoNoExito("No ha seleccionado cliente");
            return;
        }
        Pruebas.getInstancia().setClienteAMostrar(clienteToShow);
        Stage stage = (Stage) clientes.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/clientServices/ModificarCliente.fxml");
    }
    public void cerrarVentana(){
        Stage stage = (Stage) clientes.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual      
    }
    private void mostrarInfoNoExito(String info) {
        
        GeneralAlert g;
        
        g = new WarningAlert();
        
        g.setMensaje(info);
        
        g.showAlert();
        
    }
}
