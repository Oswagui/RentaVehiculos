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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.connection.Conexion;
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

    @FXML
    void atras(MouseEvent event) {

    }

    @FXML
    void buscar(MouseEvent event) throws IOException, SQLException {
        
        Conexion conn = new Conexion();
        ResultSet vehiculos=null;              
        listaVehiculos=FXCollections.observableArrayList();
        Proveedor p;
        String matriculaG=matricula.getText();
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
              
        try{
            cst = conn.getConnection().
                prepareCall("{call  consultarVehiculo(?,?,?,?,?,?,?,?,?,?,?)}");
           
            if(!matriculaG.equals("")){
                cst.setString(1, matriculaG);
            }else{
                cst.setNull(1,java.sql.Types.VARCHAR);
            }
            
            if(!tipoG.equals("")){
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
            
            if(!colorG.equals("")){
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
            
            Stage stage = (Stage) matricula.getScene().getWindow();
            //stage.close(); //Quitar Comentario para cerrar la ventana actual
            Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/vehicles/ListarVehiculos.fxml");
            
            
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

    @FXML
    void limpiar(MouseEvent event) {
        matricula.setText("");
        tipo.getSelectionModel().select("");
        marca.setText("");
        modelo.setText("");
        color.getSelectionModel().select("");
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
        tipo.getItems().removeAll(tipo.getItems());
        tipo.getItems().addAll("Auto", "Todo-Terreno", "Camioneta", "Van", "Liviano-Camion","Pesado-Camion","Bus");
        tipo.getSelectionModel().select("");
        color.getItems().removeAll(color.getItems());
        color.getItems().addAll("Blanco", "Negro","Gris","Plata","Rojo","Azul","Marron","Amarillo","Dorado","Verde","Violeta","Beige");
        color.getSelectionModel().select("");
    }
    
    
    
}
