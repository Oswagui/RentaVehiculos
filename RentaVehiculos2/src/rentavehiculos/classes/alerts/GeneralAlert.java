/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.classes.alerts;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Oswaldo
 */
public abstract class GeneralAlert {
    
    private String encabezado;
    
    private String mensaje;

    public GeneralAlert() {};
    
    public GeneralAlert(String encabezado, String mensaje) {
        this.encabezado = encabezado;
        this.mensaje = mensaje;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public abstract void showAlert();
    
    protected static void setIcon(Alert alert, String path) {
        Stage stage;
        stage = (Stage) alert.getDialogPane().getScene().getWindow();
        Image ico;
        ico = new Image(GeneralAlert.class.getClassLoader().getResource(path)
                .toExternalForm());
        stage.getIcons().add(ico);
    }
    
}
