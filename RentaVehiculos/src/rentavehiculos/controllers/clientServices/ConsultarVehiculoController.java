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
import java.util.regex.Matcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.classes.validaciones.Validaciones;
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
    
    private ObservableList<String> colores;
    private ObservableList<String> tipos;
    private ObservableList<String> matriculas;
    private int[] años;
    private int[] capacidad;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Conexion cn = new Conexion();
        try {
            this.createColorList(Vehiculo.getListadoColores(cn));
        } catch (SQLException ex) {
            System.out.println("error colores");
        }
        try {
            this.createTipoList(Vehiculo.getListadoTipos(cn));
        } catch (SQLException ex) {
            System.out.println("Error TIpos");
        }
        try {
            this.createMatriculaList(Vehiculo.getListadoMatriculas(cn));
        } catch (SQLException ex) {
            System.out.println("Error Matriculas");
        }
        
        this.color.setItems(this.colores);
        this.color.getSelectionModel().selectFirst();
        this.tipo.setItems(this.tipos);
        this.tipo.getSelectionModel().selectFirst();
        this.años = Vehiculo.getRangoAños(cn);
        this.capacidad = Vehiculo.getRangoCapacidad(cn);
    }
    
    public void retroceder(){
        System.out.println("Atras");
    }
    
    public void limpiarCampos(){
        System.out.println("Limpiar");
    }
    
    public void buscarVehiculo(){
        System.out.print("Matricula --> ");
        System.out.println(this.validarMatricula(this.matricula.getText().trim()));
        System.out.print("Años --> ");
        System.out.println(this.validarAños(this.añoFin.getText().trim(),
                                            this.añoInicio.getText().trim()));
    }
    
    private void createColorList(ResultSet r) throws SQLException {
        
        ObservableList<String> listaColores = FXCollections.observableArrayList();
        listaColores.add("Todos");
        while (r.next()) {
            
            String color = r.getString(1);
            
            listaColores.add(color);
            
        }
        this.colores = listaColores;
        
    }
    
    private void createTipoList(ResultSet r) throws SQLException {
        
        ObservableList<String> listaTipos = FXCollections.observableArrayList();
        listaTipos.add("Todos");
        while (r.next()) {
            
            String tipo = r.getString(1);
            
            listaTipos.add(tipo);
            
        }
        this.tipos = listaTipos;
        
    }
    
    private void createMatriculaList(ResultSet r) throws SQLException {
        
        ObservableList<String> listaMatriculas = FXCollections.observableArrayList();
        listaMatriculas.add("Todos");
        while (r.next()) {
            
            String matricula = r.getString(1);
            
            listaMatriculas.add(matricula);
            
        }
        this.matriculas = listaMatriculas;
        System.out.println(this.matriculas.toString());
        
    }
    private boolean validarMatricula(String matricula) {
        Matcher encajaMatricula;
        encajaMatricula = Validaciones.obtenerMatcher("[A-Za-z]{3}[-][0-9]{3,4}", matricula);

        return encajaMatricula.matches();
    }
    
    private boolean validarAños(String añoFin, String añoInicio) {
        Matcher encajaAñoI,encajaAñoF;
        encajaAñoI = Validaciones.obtenerMatcher("[0-9]*", añoInicio);
        encajaAñoF = Validaciones.obtenerMatcher("[0-9]*", añoFin);
        return encajaAñoI.matches() && encajaAñoF.matches();
    }
    
    private boolean validarRangoCapacidad(String capacidadMin, String capacidadMax) {
        Matcher encajaCapMin,encajaCapMax;
        encajaCapMin = Validaciones.obtenerMatcher("[0-9]*", capacidadMin);
        encajaCapMax = Validaciones.obtenerMatcher("[0-9]*", capacidadMax);
        return encajaCapMin.matches() && encajaCapMax.matches();
    }
    
    private boolean validarRangoPrecios(String precioMin, String precioMax) {
        Matcher encajaPrecioMin,encajaPrecioMax;
        encajaPrecioMin = Validaciones.obtenerMatcher("[0-9]*[.][0-9]*", precioMin);
        encajaPrecioMax = Validaciones.obtenerMatcher("[0-9]*[.][0-9]*", precioMax);
        return encajaPrecioMin.matches() && encajaPrecioMax.matches();
    }
    
}
