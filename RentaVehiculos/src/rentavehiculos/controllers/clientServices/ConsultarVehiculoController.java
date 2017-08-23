/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.clientServices;

import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.classes.vehiculo.Vehiculo;

/**
 *
 * @author Osw
 */
public class ConsultarVehiculoController implements Initializable{
    
    @FXML
    private TextField matricula;
    
    @FXML
    private ComboBox<String> tipo;
    
    @FXML
    private TextField añoInicio;
    
    @FXML
    private TextField añoFin;
    
    @FXML
    private ComboBox<String> color;
    
    @FXML
    private TextField capacidadMinima;
    
    @FXML
    private TextField capacidadMaxima;
    
    @FXML
    private TextField precioMinimo;
    
    @FXML
    private TextField precioMaximo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Conexion cn = new Conexion();
        try {
            this.color.setItems(this.createColorList(Vehiculo.getListadoColores(cn)));
        } catch (SQLException ex) {
            System.out.println("error colores");
        }
        try {
            this.tipo.setItems(this.createColorList(Vehiculo.getListadoTipos(cn)));
        } catch (SQLException ex) {
            System.out.println("Error TIpos");
        }
        
        
    }
    
    public void retroceder(){
        System.out.println("Atras");
    }
    
    public void limpiarCampos(){
        System.out.println("Limpiar");
    }
    
    public void buscarVehiculo(){
        System.out.println("Buscar");
    }
    
    private ObservableList<String> createColorList(ResultSet r) throws SQLException {
        
        ObservableList<String> listaColores = FXCollections.observableArrayList();
        listaColores.add("");
        while (r.next()) {
            
            String color = r.getString(1);
            
            listaColores.add(color);
            
        }
        return listaColores;
        
    }
    
    private ObservableList<String> createTipoList(ResultSet r) throws SQLException {
        
        ObservableList<String> listaTipos = FXCollections.observableArrayList();
        listaTipos.add("");
        while (r.next()) {
            
            String color = r.getString(1);
            
            listaTipos.add(color);
            
        }
        return listaTipos;
        
    }
    
    
    
}
