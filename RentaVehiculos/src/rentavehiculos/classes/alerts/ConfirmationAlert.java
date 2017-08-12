/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.classes.alerts;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Oswaldo
 */
public class ConfirmationAlert extends GeneralAlert {
    
    private Alert alert;
    private Optional<ButtonType> result;
    
    public ConfirmationAlert() {
        super();
    }
    
    public ConfirmationAlert(String encabezado, String mensaje) {
        super(encabezado, mensaje);
    } 

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public Optional<ButtonType> getResult() {
        return result;
    }

    public void setResult(Optional<ButtonType> result) {
        this.result = result;
    }
    
    @Override
    public void showAlert() {
        this.alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(super.getEncabezado());
        alert.setContentText(super.getMensaje());
        
        //GeneralAlert.setIcon(alert, "smartfood/images/info.png");
        this.result = alert.showAndWait();
    }
    
}
