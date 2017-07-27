/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.classes.alerts;

import javafx.scene.control.Alert;

/**
 *
 * @author Oswaldo
 */
public class InfoAlert extends GeneralAlert {

    public InfoAlert() {
        super();
    }
    
    public InfoAlert(String encabezado, String mensaje) {
        super(encabezado, mensaje);
    } 
    
    @Override
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(super.getEncabezado());
        alert.setContentText(super.getMensaje());
        
        //GeneralAlert.setIcon(alert, "smartfood/images/info.png");
        alert.showAndWait();
    }
    
    
}
