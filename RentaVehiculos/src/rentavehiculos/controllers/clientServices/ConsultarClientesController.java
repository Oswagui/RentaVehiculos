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
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.alerts.GeneralAlert;
import rentavehiculos.classes.alerts.WarningAlert;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.classes.validaciones.Validaciones;
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
        
        if(rucCiG.equals("Persona") && !this.validarCedula(identificacionG)){
            this.mostrarInfoNoExito("El numero de cedula debe ser de 10 digitos numericos.");
        }else if (rucCiG.equals("Corporación") && !this.validarRUC(identificacionG)){
            this.mostrarInfoNoExito("El numero de RUC debe ser de 13 digitos numericos.");
        }else if (rucCiG.equals("") && !(this.validarRUC(identificacionG) || this.validarCedula(identificacionG)) ){
            this.mostrarInfoNoExito("La identificacion no corresponde a una cedula ni a un RUC validos");
        }else if(!this.validarEnteroPositivo(rdesdeG, -1, -1) || !this.validarEnteroPositivo(rhastaG, -1, -1)){
            this.mostrarInfoNoExito("Los valoes de rango Rentas deben ser enteros positivos");
        }else if(!this.validarDecimalPositivo(dgdesdeG, -1, -1) || !this.validarDecimalPositivo(dghastaG, -1, -1)){
            this.mostrarInfoNoExito("Los valoes de dinero gastado deben ser valores numericos positivos");
        }else{
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
                if(listaClientes.isEmpty()){ this.mostrarInfoNoExito("No se encontraron coincidencias con los parametros de busqueda utilizados.");}
                else{
                    cerrarVentana();
                    Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/clientServices/ListarClientes.fxml");
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
                    if(clientes!=null){
                        clientes.close();
                    }
                }
                catch(SQLException e){

                }

            }
        }
        
    }
    
    @FXML
    private void atras(MouseEvent Event) throws Throwable{       
       Stage stage = (Stage) rDesde.getScene().getWindow();
       Pruebas.getInstancia().setFuncionalidad(null);
       Pruebas.getInstancia().getSubmenu().show();
       stage.close();
    }
    
    @FXML
    private void limpiar(MouseEvent Event){
        identificacion.setText("");
        tipo.getSelectionModel().selectFirst();
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
        tipo.getItems().addAll("","Persona", "Corporación");
        tipo.getSelectionModel().selectFirst();
        
        
    }
    
    private boolean validarCedula(String cedula) {
        Matcher encajaCedula;
        encajaCedula = Validaciones.obtenerMatcher("[0-9]{10}", cedula);
        return encajaCedula.matches() || cedula.equals("");
    }
    
    private boolean validarRUC(String RUC) {
        Matcher encajaRUC;
        encajaRUC = Validaciones.obtenerMatcher("[0-9]{13}", RUC);
        return encajaRUC.matches() || RUC.equals("");
    }
    
    private boolean validarEnteroPositivo(String numero, int inf, int sup) {
        Matcher encajaEnteroPositivo;
        encajaEnteroPositivo = Validaciones.obtenerMatcher("[0-9]*", numero);
        if(!encajaEnteroPositivo.matches())
            return false;
        if(inf == -1 && sup == -1)
            return true;
        return Integer.parseInt(numero) <= sup && Integer.parseInt(numero) >= inf;
    }
    
    private boolean validarDecimalPositivo(String precio, float inf, float sup) {
        Matcher encajaDecimalPositivo;
        encajaDecimalPositivo = Validaciones.obtenerMatcher("[0-9]*[.]{0,1}[0-9]*", precio);
        if(!encajaDecimalPositivo.matches())
            return false;
        if(inf == -1 && sup == -1)
            return true;
        return Float.parseFloat(precio) >= inf && Float.parseFloat(precio) <= sup;
    }
    
    private void mostrarInfoNoExito(String info) {
        
        GeneralAlert g;
        
        g = new WarningAlert();
        
        g.setMensaje(info);
        
        g.showAlert();
        
    }
    
    public void cerrarVentana(){
        Stage stage = (Stage) identificacion.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
    }
}
