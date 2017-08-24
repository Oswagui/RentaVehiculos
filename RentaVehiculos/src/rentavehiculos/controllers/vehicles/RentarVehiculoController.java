/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.vehicles;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.alerts.GeneralAlert;
import rentavehiculos.classes.alerts.WarningAlert;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.classes.validaciones.Validaciones;
import static rentavehiculos.controllers.empleados.InsertarEmpleadosController.stringToTimestamp;
import rentavehiculos.creators.AnyCreator;
import rentavehiculos.entities.Vehiculo;

/**
 *
 * @author Uchiha ClanPc
 */
public class RentarVehiculoController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField matricula;
    
    @FXML
    private TextField precio;
    
    @FXML
    private TextField cliente;
    
    @FXML
    private TextField dias;
    
    @FXML
    private TextField total;
    
    @FXML
    private ImageView imagen;
    
    @FXML
    private Button rentarButton;
    
    private KeyEvent anterior = null;
    private float vTotal = 0;
    
    public void setApp(Stage app) {
        this.app = app;
    }

    @FXML
    void atras(MouseEvent event) throws IOException {
        Stage stage = (Stage) matricula.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        Pruebas.getInstancia().setRentar(null);
        Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/vehicles/InfoVehiculo.fxml");
        
        
    }
    
    @FXML
    void rentar(MouseEvent event) throws IOException {
        String matriculaG = matricula.getText();
        String clienteG = cliente.getText();
        float totalG = Float.valueOf(total.getText());
        String numDiasG = this.dias.getText();
        if(clienteG.equals("") || numDiasG.equals("")){
            this.mostrarInfoNoExito("Existen campos vacios por favor llenelos");
        }else if(!this.validarCedula(clienteG) && !this.validarRUC(clienteG)){
            this.mostrarInfoNoExito("RUC/CEDULA no valido");
        }else if(totalG <= 0){
            this.mostrarInfoNoExito("Ingrese un numero de dias valido");
        }else if(this.verificarIdentificacion(clienteG) < 1){
            Stage stage = (Stage) matricula.getScene().getWindow();
            Pruebas.getInstancia().setRentar(stage);
            stage.hide();
            Stage ventana = null;
            try {
                ventana = AnyCreator.anyCreator("src/rentavehiculos/screens/clientServices/AgregarCliente.fxml");
            } catch (IOException ex) {
                System.out.println("Error");
            }
            ventana.show();
        }else {
            this.mostrarInfoNoExito("Rentado");
            CallableStatement cst=null;
            Conexion conn = new Conexion();
            try{
                cst = conn.getConnection().prepareCall("{call rentar(?,?,?,?,?,?,?,?)}");
                cst.setInt(1,this.maximaRenta() +1);
                cst.setInt(2, Integer.parseInt(numDiasG));
                //validar fecha formato dd/mm/yyyy
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    cst.setTimestamp(3, stringToTimestamp(dateFormat.format(date)));
                } catch (ParseException ex) {
                    cst.setNull(3, java.sql.Types.DATE);
                }
                cst.setInt(4,20000);
                cst.setInt(5, 100);
                cst.setString(6, matriculaG);
                cst.setString(7, clienteG);
                cst.setFloat(8, totalG);

                cst.execute();
                
                
                //stage.close(); //Quitar Comentario para cerrar la ventana actual
                //Pruebas.getInstancia().mostrarAnyVentana("");  //menu principal
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
                this.mostrarInfoNoExito("No se pudo rentar");
            } 
            finally {
                try {
                    conn.desconexion();
                    if(cst!=null){
                       cst.close();
                    }
                }
                catch(SQLException e){

                }

            }
        }
        
    }
    
    @FXML
    void calcularTotal(KeyEvent event) throws IOException {
        if(event.getCode().toString().equalsIgnoreCase("BACK_SPACE")){
            if(this.dias.getText().length() == 0){
                this.total.setText("0");
            }
            return;
        }
        
        if(!event.getCode().isDigitKey()){
            return;
        }
        if(this.dias.getText().length() == 0){
                this.dias.setText("");
                this.total.setText("");
            }
        float precioDiaG = Float.valueOf(precio.getText().substring(1));
        System.out.println(precioDiaG);
        String numDiasG = this.dias.getText().concat(event.getText());
        if(!this.validarEnteroPositivo(numDiasG, -1, -1)){
            this.mostrarInfoNoExito("El numero de Dias debe ser un entero positivo");
        }else{
            System.out.println(numDiasG);
            float Vtotal = Float.parseFloat(numDiasG);
            Vtotal = Vtotal * precioDiaG;
            this.total.setText(String.valueOf(Vtotal));
        }
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vehiculo vehicleToShow=Pruebas.getInstancia().getVehiculoAMostrar();
        matricula.setText(vehicleToShow.getMatricula());
        precio.setText(vehicleToShow.getPrecioS());
        dias.setText("0");
        Image fotoVehiculo=new Image(new ByteArrayInputStream(vehicleToShow.getFoto()));
        imagen.setImage(fotoVehiculo);
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
    
    private int verificarIdentificacion(String identifiacion){
        Conexion cn = new Conexion();
        int cantidad = -1;
        try{
            // Llamada al procedimiento almacenado
            CallableStatement cst = cn.getConnection().
                    prepareCall("{call  encontrarIdentificacion(?,?)}");
            // Parametro 1 del procedimiento almacenado
            
            cst.setString(1, identifiacion);
            // Definimos los tipos de los parametros de salida del procedimiento almacenado
            cst.registerOutParameter(2, java.sql.Types.INTEGER);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            // Se obtienen la salida del procedimineto almacenado
            
            cantidad = cst.getInt(2);
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        finally {
            cn.desconexion();
        }
        return cantidad;
    }
    
    private int maximaRenta(){
        Conexion cn = new Conexion();
        int maximo = 0;
        try{
            // Llamada al procedimiento almacenado
            CallableStatement cst = cn.getConnection().
                    prepareCall("{call  encontrarMaximoCodigoRenta(?)}");
            // Parametro 1 del procedimiento almacenado
            
            // Definimos los tipos de los parametros de salida del procedimiento almacenado
            cst.registerOutParameter(1, java.sql.Types.INTEGER);
            // Ejecuta el procedimiento almacenado
            cst.execute();
            // Se obtienen la salida del procedimineto almacenado
            
            maximo = cst.getInt(1);
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        finally {
            cn.desconexion();
        }
        return maximo;
    }
}
