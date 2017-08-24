/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.vehicles;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.alerts.GeneralAlert;
import rentavehiculos.classes.alerts.WarningAlert;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.classes.validaciones.Validaciones;
import rentavehiculos.entities.Proveedor;
import rentavehiculos.entities.Vehiculo;

/**
 *
 * @author Uchiha ClanPc
 */
public class ConsultarVehiculoController implements Initializable{
    
    private Stage app;
    
    ObservableList<Vehiculo> listaVehiculos;
    
    @FXML
    private BorderPane pane;
    
    @FXML
    private TextField matricula;
    
    @FXML
    private ComboBox<String> tipo;
    
    @FXML
    private TextField marca;
    
    @FXML
    private TextField modelo;
    
    @FXML
    private ComboBox<String> color;

    //añoDesde-añoHasta
    @FXML
    private TextField aDesde;
    
    @FXML
    private TextField aHasta;
    
    //capacidadDesde-capacidadHasta
    @FXML
    private TextField cDesde;
    
    @FXML
    private TextField cHasta;
    
    //precioDesde-precioHasta
    @FXML
    private TextField pDesde;
    
    @FXML
    private TextField pHasta;  
    private ObservableList<String> colores;
    private ObservableList<String> tipos;

    @FXML
    void atras(MouseEvent event) {
       Stage stage = (Stage) aDesde.getScene().getWindow();
       Pruebas.getInstancia().setFuncionalidad(null);
       Pruebas.getInstancia().getSubmenu().show();
       stage.close();

    }

    @FXML
    void buscar(MouseEvent event) throws IOException, SQLException {
        
        Conexion conn = new Conexion();
        ResultSet vehiculos=null;              
        listaVehiculos=FXCollections.observableArrayList();
        Proveedor p;
        String matriculaG=matricula.getText().trim();
        String tipoG=tipo.getSelectionModel().getSelectedItem().toString();
        String marcaG=marca.getText();
        String nombre_modeloG=modelo.getText();
        String colorG=color.getSelectionModel().getSelectedItem().toString();
        String adesdeG=aDesde.getText();
        String ahastaG=aHasta.getText();
        String cdesdeG=cDesde.getText();
        String chastaG=cHasta.getText();
        String pdesdeG=pDesde.getText();
        String phastaG=pHasta.getText();
        CallableStatement cst=null;
        
        if(!this.validarMatricula(matriculaG)){
            this.mostrarInfoNoExito("El formato de la matricula es incorrecto.\n Ej-Correcto: ABC-1234");
        }else if(!this.validarAños(adesdeG, ahastaG)){
            this.mostrarInfoNoExito("Los valores del rango de años deben ser enteros y validos");
        }else if(!this.validarRangoCapacidad(cdesdeG, chastaG)){
            this.mostrarInfoNoExito("Los valores del rango de capacidad deben ser enteros y validos");
        }else if(!this.validarRangoPrecios(pdesdeG, phastaG)){
            this.mostrarInfoNoExito("Los valores del rango de precios deben ser numericos y validos");
        }else{
            try{
                cst = conn.getConnection().
                    prepareCall("{call  consultarVehiculo(?,?,?,?,?,?,?,?,?,?,?)}");

                if(!matriculaG.equals("")){
                    cst.setString(1, matriculaG);
                }else{
                    cst.setNull(1,java.sql.Types.VARCHAR);
                }

                if(!tipoG.equals("Todos")){
                    cst.setString(2, tipoG);
                }else{
                    cst.setNull(2,java.sql.Types.VARCHAR);
                }

                if(!marcaG.equals("")){
                    cst.setString(3, marcaG);
                }else{
                    cst.setNull(3,java.sql.Types.VARCHAR);
                }

                if(!nombre_modeloG.equals("")){
                    cst.setString(4, nombre_modeloG);
                }else{
                    cst.setNull(4,java.sql.Types.VARCHAR);
                }

                if(!colorG.equals("Todos")){
                    cst.setString(5, colorG);
                }else{
                    cst.setNull(5,java.sql.Types.VARCHAR);
                }

                if(!adesdeG.equals("")){
                    //validar que tiene que ser un número
                    cst.setInt(6, Integer.parseInt(adesdeG));
                }else{
                    cst.setNull(6,java.sql.Types.INTEGER);
                }

                if(!ahastaG.equals("")){
                    //validar que tiene que ser un número
                    cst.setInt(7, Integer.parseInt(ahastaG));
                }else{
                    cst.setNull(7,java.sql.Types.INTEGER);
                }

                if(!cdesdeG.equals("")){
                    //validar que tiene que ser un número
                    cst.setInt(8, Integer.parseInt(cdesdeG));
                }else{
                    cst.setNull(8,java.sql.Types.INTEGER);
                }

                if(!chastaG.equals("")){
                    //validar que tiene que ser un número
                    cst.setInt(9, Integer.parseInt(chastaG));
                }else{
                    cst.setNull(9,java.sql.Types.INTEGER);
                }

                if(!pdesdeG.equals("")){
                    //validar que tiene que ser un número
                    cst.setFloat(10, Float.parseFloat(pdesdeG));
                }else{
                    cst.setNull(10,java.sql.Types.FLOAT);
                }

                if(!phastaG.equals("")){
                    //validar que tiene que ser un número
                    cst.setFloat(11, Float.parseFloat(phastaG));
                }else{
                    cst.setNull(11,java.sql.Types.FLOAT);
                }

                boolean hayResultados=cst.execute();
                while(hayResultados){
                    vehiculos=cst.getResultSet();
                    while(vehiculos.next()){
                        Vehiculo vehiculoTemp=new Vehiculo
                                                 (vehiculos.getString("matricula"));                                       
                        p = new Proveedor(vehiculos.getInt("proveedor"));                                    
                        CallableStatement cst2=null;

                        try{
                            cst2 = conn.getConnection().prepareCall
                                        ("{call  getNombreProveedor(?,?)}");

                            if(p.getIdProveedor()!=null){
                                cst2.setInt(1, p.getIdProveedor());
                            }else{
                                cst2.setNull(1,java.sql.Types.INTEGER);                      
                            }

                            cst2.registerOutParameter(2, java.sql.Types.VARCHAR);
                            cst2.execute();
                            p.setNombre(cst2.getString(2));

                        }
                        catch(SQLException e){
                            System.out.println(e.getMessage());
                        }                   
                        finally{
                            if(cst2!=null){
                                cst2.close();
                            }
                        }

                        vehiculoTemp.setProveedor(p);
                        vehiculoTemp.setTipo(vehiculos.getString("tipo"));
                        vehiculoTemp.setMarca(vehiculos.getString("marca"));
                        vehiculoTemp.setAño(vehiculos.getInt("año"));
                        vehiculoTemp.setNombreModelo(vehiculos.
                                                    getString("nombre_modelo"));
                        if(vehiculos.getBoolean("disponibilidad")){
                            vehiculoTemp.setEstado("Disponible");
                        }else if(vehiculos.getBoolean("disponibilidad")==false){
                            vehiculoTemp.setEstado("No Disponible");
                        }else{
                            vehiculoTemp.setEstado("Desconocido");
                        }
                        vehiculoTemp.setDisponibilidad(vehiculos.
                                              getBoolean("disponibilidad"));
                        vehiculoTemp.setColor(vehiculos.getString("color"));
                        vehiculoTemp.setCapacidad(vehiculos.getInt("capacidad"));
                        vehiculoTemp.setPrecio(vehiculos.getFloat("precio"));
                        vehiculoTemp.setFoto(vehiculos.getBytes("foto"));                   

                        listaVehiculos.add(vehiculoTemp);

                    }
                    hayResultados=cst.getMoreResults();
                }                    
                Pruebas.getInstancia().setListaVehiculos(listaVehiculos);
                System.out.println(listaVehiculos.size());
                if(listaVehiculos.isEmpty()){ this.mostrarInfoNoExito("No se encontraron coincidencias con los parametros de busqueda utilizados.");}
                else{
                    cerrarVentana();
                    Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/vehicles/ListarVehiculos.fxml");
                }

                

            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } 
            finally {
                try {
                    conn.desconexion();
                    if(cst!=null){
                       cst.close();
                    }
                    if(vehiculos!=null){
                        vehiculos.close();
                    }
                }
                catch(SQLException e){

                }

            }
        }

    }

    @FXML
    void limpiar(MouseEvent event) {
        matricula.setText("");
        tipo.getSelectionModel().selectFirst();
        marca.setText("");
        modelo.setText("");
        color.getSelectionModel().selectFirst();
        aDesde.setText("");
        aHasta.setText("");
        cDesde.setText("");
        cHasta.setText("");
        pDesde.setText("");
        pHasta.setText("");
        

    }
    
    
    public void setApp(Stage app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Conexion cn = new Conexion();
        try {
            this.createColorList(rentavehiculos.classes.vehiculo.Vehiculo.getListadoColores(cn));
        } catch (SQLException ex) {
            System.out.println("error colores");
        }
        try {
            this.createTipoList(rentavehiculos.classes.vehiculo.Vehiculo.getListadoTipos(cn));
        } catch (SQLException ex) {
            System.out.println("Error TIpos");
        }
        
        this.color.setItems(this.colores);
        this.color.getSelectionModel().selectFirst();
        this.tipo.setItems(this.tipos);
        this.tipo.getSelectionModel().selectFirst();
    }
    
    public void cerrarVentana(){
        Stage stage = (Stage) matricula.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
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
    
    
    private boolean validarMatricula(String matricula) {
        Matcher encajaMatricula;
        encajaMatricula = Validaciones.obtenerMatcher("[A-Za-z]{3}[-][0-9]{3,4}", matricula);
        return encajaMatricula.matches() || matricula.equals("");
    }
    
    private boolean validarAños(String añoFin, String añoInicio) {
        Matcher encajaAñoI,encajaAñoF;
        encajaAñoI = Validaciones.obtenerMatcher("[0-9]*", añoInicio);
        encajaAñoF = Validaciones.obtenerMatcher("[0-9]*", añoFin);
        return (encajaAñoI.matches() || añoInicio.equals("")) 
                && (encajaAñoF.matches() || añoFin.equals(""));
    }
    
    private boolean validarRangoCapacidad(String capacidadMin, String capacidadMax) {
        Matcher encajaCapMin,encajaCapMax;
        encajaCapMin = Validaciones.obtenerMatcher("[0-9]*", capacidadMin);
        encajaCapMax = Validaciones.obtenerMatcher("[0-9]*", capacidadMax);
        return (encajaCapMin.matches() || capacidadMin.equals("")) 
                && (encajaCapMax.matches() || capacidadMax.equals(""));
    }
    
    private boolean validarRangoPrecios(String precioMin, String precioMax) {
        Matcher encajaPrecioMin,encajaPrecioMax;
        encajaPrecioMin = Validaciones.obtenerMatcher("[0-9]*[.]{0,1}[0-9]*", precioMin);
        encajaPrecioMax = Validaciones.obtenerMatcher("[0-9]*[.]{0,1}[0-9]*", precioMax);
        return (encajaPrecioMin.matches() || precioMin.equals("")) 
                && (encajaPrecioMax.matches() || precioMax.equals(""));
    }
    
    private void mostrarInfoNoExito(String info) {
        
        GeneralAlert g;
        
        g = new WarningAlert();
        
        g.setMensaje(info);
        
        g.showAlert();
        
    }
}
