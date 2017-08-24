/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.vehicles;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rentavehiculos.Pruebas;
import rentavehiculos.classes.alerts.GeneralAlert;
import rentavehiculos.classes.alerts.InfoAlert;
import rentavehiculos.classes.alerts.WarningAlert;
import rentavehiculos.classes.validaciones.Validaciones;
import rentavehiculos.entities.Vehiculo;

/**
 *
 * @author Uchiha ClanPc
 */
public class ModificarVehiculoController implements Initializable{
    private Stage app;
    
    @FXML
    private TextField marca;

    @FXML
    private TextField tipo;

    @FXML
    private TextField disponibilidad;

    @FXML
    private TextField precio;

    @FXML
    private TextField color;

    @FXML
    private TextField matricula;

    @FXML
    private ImageView imagen;

    @FXML
    private TextField proveedor;

    @FXML
    private TextField modelo;

    @FXML
    private TextField año;

    @FXML
    private TextField capacidad;
    
    private File file;
    
    public void setApp(Stage app) {
        this.app = app;
    }

    @FXML
    void atras(MouseEvent event) throws IOException {
        Stage stage = (Stage) matricula.getScene().getWindow();
        stage.close(); //Quitar Comentario para cerrar la ventana actual
        Pruebas.getInstancia().mostrarAnyVentana("src/rentavehiculos/screens/vehicles/ListarVehiculos.fxml");
        
    }
    
    @FXML
    void seleccionarImagen(MouseEvent event) throws IOException {
       FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("Abrir Imagen");
       fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif", 
                        "*.bmp"),
                new FileChooser.ExtensionFilter("Todos los Archivos", "*.*"));
        this.file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
        
        if (this.file != null) {
            
            Image img = new Image(this.file.toURI().toString());
            this.imagen.setImage(img);
            
        }
    }
    
    @FXML
    void guardar(MouseEvent event){
        String matriculaG = matricula.getText().trim();
        String tipoG=tipo.getText();
        String marcaG=marca.getText();
        String nombre_modeloG=modelo.getText();
        String colorG=color.getText();
        String añoG=año.getText();
        String capacidadG=capacidad.getText();
        String precio=this.precio.getText();
        
        if(!this.validarEnteroPositivo(añoG, 0, 2017)){
            this.mostrarInfoNoExito("Año ingresado no valido, debe ser un entero positivo y su valor debe ser menor o igual al año actual.");
        }else if(!this.validarEnteroPositivo(capacidadG, -1, -1)){
            this.mostrarInfoNoExito("Capacidad ingresado no valido, debe ser un entero positivo.");
        }
        else if(!this.validarDecimalPositivo(precio, -1, -1)){
            this.mostrarInfoNoExito("Precio ingresado no valido, debe ser un numero positivo.");
        }else{
            System.out.println("Guardando");
            //Programa Funcionalidad de Guardar
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vehiculo vehicleToShow=Pruebas.getInstancia().getVehiculoAMostrar();
        matricula.setText(vehicleToShow.getMatricula());
        proveedor.setText(vehicleToShow.getProveedor().getNombre());
        tipo.setText(vehicleToShow.getTipo());
        marca.setText(vehicleToShow.getMarca());
        año.setText(vehicleToShow.getAño().toString());
        modelo.setText(vehicleToShow.getNombreModelo());
        disponibilidad.setText(vehicleToShow.getEstado());
        color.setText(vehicleToShow.getColor());
        capacidad.setText(vehicleToShow.getCapacidad().toString());
        precio.setText(vehicleToShow.getPrecio().toString());
        
        Image fotoVehiculo=new Image(new ByteArrayInputStream(vehicleToShow.getFoto()));
        imagen.setImage(fotoVehiculo);
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
    private void mostrarInfoExito() {
        
        GeneralAlert g;
                
        g = new InfoAlert();

        g.setMensaje("Valores Actualizados con exito");

        g.showAlert();
        
    }
    
    
}
