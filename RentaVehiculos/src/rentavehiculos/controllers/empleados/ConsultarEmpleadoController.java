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
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.classes.connection.Conexion;
import static rentavehiculos.controllers.empleados.InsertarEmpleadosController.stringToTime;
import static rentavehiculos.controllers.empleados.InsertarEmpleadosController.stringToTimestamp;

/**
 *
 * @author Harold Aragón
 */


public class ConsultarEmpleadoController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField nombre;
    
    @FXML
    private TextField apellido;
    
    @FXML
    private TextField cedula;
    
    @FXML
    private TextField sueldoDesde;
    
    @FXML
    private TextField sueldoHasta;
    
    @FXML
    private TextField fecha_DesdeI;
    
    @FXML
    private TextField fecha_HastaI;
    
    @FXML
    private TextField puesto;
    
    @FXML
    private TextField grupoTrabajo;
    
    @FXML
    private TextField horaE;
    
    @FXML
    private TextField horaS; 
    
    public void setApp(Stage app) {
        this.app = app;
    }
    
    @FXML
    public void atras(MouseEvent event) throws IOException{
        Stage stage = (Stage) horaS.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar ventana actual
        //Pruebas.getInstancia().mostrarAnyVentana("fxml de ventana anterior");
        
    }
        
    @FXML
    private void buscar(MouseEvent event) throws IOException, SQLException {
        Conexion conn = new Conexion();
        String nombreG=nombre.getText();
        String apellidoG=apellido.getText();
        String cedulaG=cedula.getText();
        String sueldoDesdeG=sueldoDesde.getText();
        String sueldoHastaG=sueldoHasta.getText();
        String fecha_DesdeIG=fecha_DesdeI.getText();
        String fecha_HastaIG=fecha_HastaI.getText();
        String puestoG=puesto.getText();
        String grupoTrabajoG=grupoTrabajo.getText();
        String horaEG=horaE.getText();
        String horaSG=horaS.getText(); 
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
                        
            if(!sueldoDesdeG.equals("")){
                cst.setFloat(4, Float.parseFloat(sueldoDesdeG));
            }else{
                cst.setNull(4,java.sql.Types.FLOAT);
            }
            if(!sueldoHastaG.equals("")){
                cst.setFloat(5, Float.parseFloat(sueldoHastaG));
            }else{
                cst.setNull(5,java.sql.Types.FLOAT);
            }
            
            if(!fecha_DesdeIG.equals("")){
                try {
                    //validar fecha formato dd/mm/yyyy
                    cst.setTimestamp(6, stringToTimestamp(fecha_DesdeIG));
                } catch (ParseException ex) {
                    System.out.println("Error al parseo de fecha");
                }
            }else{
                    cst.setNull(6,java.sql.Types.TIMESTAMP);
            }
            if(!fecha_HastaIG.equals("")){
                try {
                    //validar fecha formato dd/mm/yyyy
                    cst.setTimestamp(7, stringToTimestamp(fecha_HastaIG));
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
        sueldoDesde.setText("");
        fecha_DesdeI.setText("");
        sueldoHasta.setText("");
        fecha_HastaI.setText("");
        puesto.setText("");
        grupoTrabajo.setText("");
        horaE.setText("");
        horaS.setText("");


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
