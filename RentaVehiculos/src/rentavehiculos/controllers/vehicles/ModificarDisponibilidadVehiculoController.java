/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.vehicles;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.connection.Conexion;
import rentavehiculos.entities.Cliente;
import rentavehiculos.entities.Vehiculo;

/**
 *
 * @author Uchiha ClanPc
 */
public class ModificarDisponibilidadVehiculoController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField matricula;
    
    @FXML
    private ComboBox estado;
    
    @FXML
    private ImageView imagen;
    
    
    
    public void setApp(Stage app) {
        this.app = app;
    }
    
    @FXML
    public void atras(MouseEvent event){
        
    }
    
    @FXML
    public void aceptar(MouseEvent event){
        Conexion conn = new Conexion();
        String matriculaG=matricula.getText();
        String estadoG=estado.getSelectionModel().getSelectedItem().toString();
        CallableStatement cst=null;
        try{
            cst = conn.getConnection().
                prepareCall("{call  modificarDisponibilidadVehiculo(?,?)}");
           
            if(!matriculaG.equals("")){
                cst.setString(1, matriculaG);
            }else{
                cst.setNull(1,java.sql.Types.VARCHAR);
            }
            if(!estadoG.equals("")){
                byte disp;
                if(estadoG.equals("Disponible")){
                    disp=1;
                    cst.setByte(2, disp);
                }
                else if(estadoG.equals("No Disponible")){
                    disp=0;
                    cst.setByte(2, disp);
                }
                
            }else{
                cst.setNull(2,java.sql.Types.TINYINT);
            }
            cst.execute();
            Stage stage = (Stage) matricula.getScene().getWindow();
            //stage.close(); //Quitar Comentario para cerrar la ventana actual
            //Pruebas.getInstancia().mostrarAnyVentana("");  //menu principal
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
    public void salir(MouseEvent event){
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vehiculo vehicleToShow=Pruebas.getInstancia().getVehiculoAMostrar();
        matricula.setText(vehicleToShow.getMatricula());
        estado.getItems().removeAll(estado.getItems());
        estado.getItems().addAll("Disponible", "No Disponible");
        estado.getSelectionModel().select(vehicleToShow.getEstado());
        Image fotoVehiculo=new Image(new ByteArrayInputStream(vehicleToShow.getFoto()));
        imagen.setImage(fotoVehiculo);

    }
    
}
