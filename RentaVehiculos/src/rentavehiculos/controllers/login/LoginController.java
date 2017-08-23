/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.controllers.login;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import rentavehiculos.classes.alerts.GeneralAlert;
import rentavehiculos.classes.alerts.InfoAlert;
import rentavehiculos.classes.alerts.WarningAlert;
import rentavehiculos.classes.constants.Constantes;
import rentavehiculos.classes.user.Usuario;
import rentavehiculos.classes.validaciones.Validaciones;
import rentavehiculos.creators.AnyCreator;

/**
 *
 * @author Oswaldo
 */
public class LoginController implements Initializable {
    
    private Usuario usuario;
    
    private Stage app;
    
    @FXML
    private TextField user;
    
    @FXML
    private PasswordField password;
//    private Label label;
    
    @FXML
    private VBox fondo;
    
    private boolean esValido;

    public void setApp(Stage app) {
        this.app = app;
    }
    
    private boolean validarIngreso(String u, String p) {
        Matcher encajaUser, encajaPass;
        encajaUser = Validaciones.obtenerMatcher("[A-Za-z0-9]{1,30}", u);
        encajaPass = Validaciones.obtenerMatcher(".{1,30}", p);

        return encajaUser.matches() && encajaPass.matches();
    }
    
    @FXML
    private void login(MouseEvent event) {
        
        Usuario usuarioSistema;
        
        ArrayList<String> validador;
        
        String u = this.user.getText().trim();
        String p = this.password.getText().trim();
        System.out.println(u + " " +p);
        this.esValido = this.validarIngreso(u, p);
        
        if (this.esValido) {
            
            validador = Usuario.buscarUsuario(u, p);

            int existeUsuario = Integer.parseInt(validador.get(0));

            if(existeUsuario == 1) {

                this.mostrarInfoIngreso();

                usuarioSistema = new Usuario();
                usuarioSistema.setUsuario(u);
                usuarioSistema.setContrasenia(p);
                usuarioSistema.setPuesto(validador.get(1));
                usuarioSistema.setIdEmpleado(Integer.parseInt(validador.get(2)));
                Stage ventana = null;
                if (usuarioSistema.getPuesto().equalsIgnoreCase("Adminin of DB")) {
                   // this.cargarAsistente(event, idRes);
                    System.out.println("ADMIN");
                }
                else if (usuarioSistema.getPuesto().equalsIgnoreCase("Servicio al Cliente")) {
                    //this.cargarCliente(event);
                    System.out.println("SERVICIO");
                    
                    try {
                        ventana = AnyCreator.anyCreator("src/rentavehiculos/screens/clientServices/SubmenuAtencion.fxml");
                    } catch (IOException ex) {
                        System.out.println("error");
                    }
                }
                else if (usuarioSistema.getPuesto().equalsIgnoreCase("Supervisor")) {
                    //this.cargarCliente(event);
                    System.out.println("SUPERVISOR");
                }
                Stage stageLogin = (Stage)((Node)this.fondo).getScene().getWindow();
                ventana.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        System.out.println("No puede cerrar la ventana asi");
                        event.consume();}
                });
                ventana.setResizable(false);
                ventana.setMaximized(false);
                ventana.showAndWait();
                stageLogin.show();
                this.limpiarCampos();
            }
            else {
                this.limpiarCampos();
                this.mostrarInfoNoExito("Usuario y/o contraseña incorrectos");
            }
        }
        else {
            this.limpiarCampos();
            this.mostrarInfoNoExito("Valores ingresados no válidos");
        }
            
    }
    
    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Validaciones.addTextLimiter(this.user, 
                Constantes.MAX_LENGHT_USER_PASS);
        Validaciones.addTextLimiter(this.password, 
                Constantes.MAX_LENGHT_USER_PASS);
    }
    
//    private void cargarAsistente(MouseEvent event, int idRes) {
//        try {
//        
//            FXMLLoader loader = new FXMLLoader(ListaCategoriaController.
//            class.getResource("/smartfood/screen/creators/AsistenteCreator.fxml"));
//            
//            BorderPane page = (BorderPane) loader.load();
//            Stage parent = (Stage) ((Node)event.getTarget()).getScene().getWindow();
//            
//            Stage dialogStage = new Stage();
//            
//            dialogStage.setTitle(parent.getTitle());
//            dialogStage.initModality(Modality.WINDOW_MODAL);
////            dialogStage.getIcons().add(parent.getIcons().get(0));
//            dialogStage.initOwner(((Node)event.getTarget()).getScene().getWindow());
//            
//            parent.close();
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//
//            AsistenteCreatorController controller = loader.getController();
//            controller.setAppStage(dialogStage);
//            controller.setIdRestaurante(idRes);
//
//            dialogStage.showAndWait();
//
//        } catch (IOException e) {
//            System.out.println("Error de carga");
//        }
//    }
//    
//    private void cargarCliente(MouseEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(ListaCategoriaController.
//            class.getResource("/smartfood/screen/creators/ClienteCreator.fxml"));
//            System.out.println("Joder");
//            BorderPane page = (BorderPane) loader.load();
//            Stage parent = (Stage) ((Node)event.getTarget()).getScene().getWindow();
//            
//            Stage dialogStage = new Stage();
//            
//            
//            dialogStage.setTitle(parent.getTitle());
//            
//            parent.close();
//            dialogStage.initModality(Modality.WINDOW_MODAL);
////            dialogStage.getIcons().add(parent.getIcons().get(0));
//            dialogStage.initOwner(((Node)event.getTarget()).getScene().getWindow());
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//
//            ClienteCreatorController controller = loader.getController();
//            controller.setAppStage(dialogStage);
//
//            dialogStage.showAndWait();
//
//        } catch (IOException e) {
//            
//            this.mostrarInfoNoExito("Error en carga");
//        
//        }
//    }
//    
    private void mostrarInfoIngreso() {
        
        GeneralAlert g;
                
        g = new InfoAlert();

        g.setMensaje("Ingreso exitoso");

        g.showAlert();
        
    }
    
    private void mostrarInfoNoExito(String info) {
        
        GeneralAlert g;
        
        g = new WarningAlert();
        
        g.setMensaje(info);
        
        g.showAlert();
        
    }
    
    private void limpiarCampos() {
        this.user.setText("");
        this.password.setText("");
    }
}
