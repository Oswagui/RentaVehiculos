/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.clientServices;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.creators.ListarClientesCreator;
import rentavehiculos.entities.Cliente;



/**
 *
 * @author Harold
 */
public class ConsultarClientesController implements Initializable{
    
    private Stage app;
    
    ObservableList<Cliente> listaClientes;
    
    @FXML
    private TextField identificacion;
    
    @FXML
    private ComboBox<String> tipo;
     
    @FXML
    private TextField rDesde;
    
    @FXML
    private TextField rHasta;
    
    @FXML
    private TextField nombre;
    
    @FXML
    private TextField rSocial;
    
    @FXML
    private TextField dgDesde;
    
    @FXML
    private TextField dgHasta;
    
    public void setApp(Stage app) {
        this.app = app;
    }  
       
    @FXML
    private void buscar(MouseEvent Event) throws IOException, SQLException{
        Conexion conn = new Conexion();
        ResultSet clientes=null;              
        listaClientes=FXCollections.observableArrayList();
        String identificacionG=identificacion.getText();
        String rucCiG=tipo.getSelectionModel().getSelectedItem().toString();
        String nombreG=nombre.getText();
        String rSocialG=rSocial.getText();
        String rdesdeG=rDesde.getText();
        String rhastaG=rHasta.getText();
        String dgdesdeG=dgDesde.getText();
        String dghastaG=dgHasta.getText();
        CallableStatement cst=null;
        
              
        try{
            cst = conn.getConnection().
                    prepareCall("{call  consultarCliente(?,?,?,?,?,?,?,?)}");
            
            if(rucCiG.equals("Corporación")){
               cst.setBoolean(1,Boolean.TRUE);
            }else if(rucCiG.equals("Persona")){
               cst.setBoolean(1,Boolean.FALSE);
            }else{
               cst.setNull(1,java.sql.Types.TINYINT);
            }
            
            if(!identificacionG.equals("")){
                cst.setString(2, identificacionG);
            }else{
                cst.setNull(2,java.sql.Types.VARCHAR);
            }
            
            if(!nombreG.equals("")){
                cst.setString(3, nombreG);
            }else{
                cst.setNull(3,java.sql.Types.VARCHAR);
            }
            
            if(!rSocialG.equals("")){
                cst.setString(4, rSocialG);
            }else{
                cst.setNull(4,java.sql.Types.VARCHAR);
            }
            
            if(!rdesdeG.equals("")){
                //validar que tiene que ser un número
                cst.setInt(5, Integer.parseInt(rdesdeG));
            }else{
                cst.setNull(5,java.sql.Types.INTEGER);
            }
            
            if(!rhastaG.equals("")){
                //validar que tiene que ser un número
                cst.setInt(6, Integer.parseInt(rhastaG));
            }else{
                cst.setNull(6,java.sql.Types.INTEGER);
            }
            
            if(!dgdesdeG.equals("")){
                //validar que tiene que ser un número
                cst.setInt(7, Integer.parseInt(dgdesdeG));
            }else{
                cst.setNull(7,java.sql.Types.INTEGER);
            }
            
            if(!dghastaG.equals("")){
                //validar que tiene que ser un número
                cst.setInt(8, Integer.parseInt(dghastaG));
            }else{
                cst.setNull(8,java.sql.Types.INTEGER);
            }
 
            boolean hayResultados=cst.execute();
            while(hayResultados){
                clientes=cst.getResultSet();
                while(clientes.next()){
                    Cliente clienteTemp=new Cliente
                            (clientes.getInt("id_cliente"));
                    
                    if(clientes.getBoolean("ruc_ci")){
                        clienteTemp.setTipo("Coorporación");
                    }else if(clientes.getBoolean("ruc_ci")==false){
                        clienteTemp.setTipo("Persona");
                    }else{
                        clienteTemp.setTipo("");
                    }
                    clienteTemp.setRucCi(clientes.getBoolean("ruc_ci"));
                    clienteTemp.setIdentificacion(clientes.getString("identificacion"));
                    clienteTemp.setNombre(clientes.getString("nombre"));
                    clienteTemp.setTelefono(clientes.getString("telefono"));
                    clienteTemp.setDireccion(clientes.getString("direccion"));
                    clienteTemp.setRazonSocial(clientes.getString("razon_social"));

                    listaClientes.add(clienteTemp);
                                      
                }
                hayResultados=cst.getMoreResults();
            }                    
            Pruebas.getInstancia().setListaClientes(listaClientes);
            Stage stage = (Stage) identificacion.getScene().getWindow();
            //stage.close(); //Quitar Comentario para cerrar la ventana actual
            Pruebas.getInstancia().mostrarNuevaVentana();
            
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
                if(clientes!=null){
                    clientes.close();
                }
            }
            catch(SQLException e){
                    
            }
            
        }
        
    }
    
    @FXML
    private void atras(MouseEvent Event) throws Throwable{       
                
    }
    
    @FXML
    private void limpiar(MouseEvent Event){
        identificacion.setText("");
        tipo.getSelectionModel().select("");
        nombre.setText("");
        rSocial.setText("");
        rDesde.setText("");
        rHasta.setText("");
        dgDesde.setText("");
        dgHasta.setText("");
    }    
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipo.getItems().removeAll(tipo.getItems());
        tipo.getItems().addAll("Persona", "Corporación");
        tipo.getSelectionModel().select("");
        
        
    }
    
}
