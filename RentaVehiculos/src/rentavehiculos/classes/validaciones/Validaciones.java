/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos.classes.validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Jose Masson
 */
public class Validaciones {
    
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((final ObservableValue<? extends String> 
                ov, final String oldValue, final String newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }
    
    public static Matcher obtenerMatcher(String url, String cadena) {
        Pattern patron;
        patron = Pattern.compile(url);
        Matcher encaja;
        encaja = patron.matcher(cadena);
        return encaja;
    }

    public static void addTextLimiter(final TextArea tf, final int maxLength) {
        tf.textProperty().addListener((final ObservableValue<? extends String> 
                ov, final String oldValue, final String newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }
    
}
