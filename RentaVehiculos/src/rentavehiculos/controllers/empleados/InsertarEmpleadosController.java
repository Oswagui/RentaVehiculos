/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.empleados;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.classes.connection.Conexion;

/**
 *
 * @author Uchiha ClanPc
 */
public class InsertarEmpleadosController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField nombre;
    
    @FXML
    private TextField apellido;
    
    @FXML
    private TextField cedula;
    
    @FXML
    private TextField usuario;
    
    @FXML
    private PasswordField contrasena; //
    
    @FXML
    private TextField sueldo;
    
    @FXML
    private TextField fecha_I;
    
    @FXML
    private TextField puesto;
    
    @FXML
    private TextField grupoTrabajo;
    
    @FXML
    private TextField horaE;
    
    @FXML
    private TextField horaS;
    
    @FXML
    private TextField telefono;
    
    @FXML
    private TextField direccion;
    
    public void setApp(Stage app) {
        this.app = app;
    }
    
    @FXML
    public void atras(MouseEvent event) throws IOException{
        Stage stage = (Stage) direccion.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar ventana actual
        //Pruebas.getInstancia().mostrarAnyVentana("fxml de ventana anterior");
        
    }
    
    
    @FXML
    public void agregar(MouseEvent Event) throws IOException, SQLException{
        
     Conexion conn = new Conexion();
     String nombreG=nombre.getText();
     String apellidoG=apellido.getText();
     String cedulaG=cedula.getText();
     String usuarioG=usuario.getText();
     String contrasenaG=contrasena.getText();
     String sueldoG=sueldo.getText();
     String fecha_IG=fecha_I.getText();
     String puestoG=puesto.getText();
     String grupoTrabajoG=grupoTrabajo.getText();
     String horaEG=horaE.getText();
     String horaSG=horaS.getText();
     String telefonoG=telefono.getText();
     String direccionG=direccion.getText();
     CallableStatement cst=null;
        
        try{
            cst = conn.getConnection().
                    prepareCall("{call  insertarEmpleado(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            if(!nombreG.equals("")){
                    cst.setString(1, nombreG);
            }else{
               cst.setNull(1,java.sql.Types.VARCHAR);
            }
            
            if(!apellidoG.equals("")){
                cst.setString(2, apellidoG);
            }else{
                cst.setNull(2,java.sql.Types.VARCHAR);
            }
            
            if(!cedulaG.equals("")){
                cst.setString(3, cedulaG);
            }else{
                cst.setNull(3,java.sql.Types.VARCHAR);
            }
            
            if(!usuarioG.equals("")){
                cst.setString(4, usuarioG);
            }else{
                cst.setNull(4,java.sql.Types.VARCHAR);
            }
            
            if(!contrasenaG.equals("")){
                cst.setString(5, contrasenaG);
            }else{
                cst.setNull(5,java.sql.Types.VARCHAR);
            }
            
            if(!sueldoG.equals("")){
                cst.setFloat(6, Float.parseFloat(sueldoG));
            }else{
                cst.setNull(6,java.sql.Types.FLOAT);
            }
            if(!fecha_IG.equals("")){
                try {
                    //validar fecha formato dd/mm/yyyy
                    cst.setTimestamp(7, stringToTimestamp(fecha_IG));
                } catch (ParseException ex) {
                    System.out.println("Error al parseo de fecha");
                }
            }else{
                    cst.setNull(7,java.sql.Types.TIMESTAMP);
            }
            if(!puestoG.equals("")){
                cst.setString(8, puestoG);
            }else{
                cst.setNull(8,java.sql.Types.VARCHAR);
            }
            
            if(!grupoTrabajoG.equals("")){
                cst.setString(9, grupoTrabajoG);
            }else{
                cst.setNull(9,java.sql.Types.VARCHAR);
            }
            
            if(!horaEG.equals("")){
                //hora formato hh:mm:ss
                cst.setTime(10, stringToTime(horaEG));
            }else{
                cst.setNull(10,java.sql.Types.TIME);
            }
            
            if(!horaSG.equals("")){
                //hora formato hh:mm:ss
                cst.setTime(11, stringToTime(horaSG));
            }else{
                cst.setNull(11,java.sql.Types.TIME);
            }
            
            if(!telefonoG.equals("")){
                cst.setString(12, telefonoG);
            }else{
                cst.setNull(12,java.sql.Types.VARCHAR);
            }
            
            if(!direccionG.equals("")){
                cst.setString(13, direccionG);
            }else{
                cst.setNull(13,java.sql.Types.VARCHAR);
            }         
            
            cst.execute();                   
            
            vaciarCampos();
            
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
            }
            catch(SQLException e){
                    
            }
            
        }
    }
    
    @FXML
    public void limpiar(MouseEvent Event) throws IOException{
        vaciarCampos();
    }
    
    public void vaciarCampos(){ 
        nombre.setText("");
        apellido.setText("");
        cedula.setText("");
        usuario.setText("");
        contrasena.setText("");
        sueldo.setText("");
        fecha_I.setText("");
        puesto.setText("");
        grupoTrabajo.setText("");
        horaE.setText("");
        horaS.setText("");
        telefono.setText("");
        direccion.setText("");

    }
    
    public static Timestamp stringToTimestamp(String fecha) throws ParseException {
    
      DateFormat formatoFecha;
      formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
      Date date = formatoFecha.parse(fecha);
      java.sql.Timestamp fechaTS = new Timestamp(date.getTime());
      return fechaTS;
      
    }
    
    public static Time stringToTime(String hora){
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");
        Date date = null;
        try {
            date = formatoHora.parse(hora);
        } catch (ParseException e) {
            System.out.println("Error de Parseo");
        }
        java.sql.Time horaT=new Time(date.getTime());
        return horaT;
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
