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
public class WarningAlert extends GeneralAlert {

    public WarningAlert() {
        super();
    }
    
    public WarningAlert(String encabezado, String mensaje) {
        super(encabezado, mensaje);
    } 
    
    @Override
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText(super.getEncabezado());
        alert.setContentText(super.getMensaje());
        
        //GeneralAlert.setIcon(alert, "smartfood/images/warning.png");
        alert.showAndWait();
    }
    
    
}
