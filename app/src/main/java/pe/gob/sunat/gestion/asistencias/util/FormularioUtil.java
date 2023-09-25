/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.util;

import java.util.function.UnaryOperator;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author mireb
 */
public class FormularioUtil {
    public static void txtMaxLenFormat(TextInputControl tf,String exp){
        UnaryOperator<TextFormatter.Change> limite = (t) -> {
            System.out.println("pe.gob.sunat.gestion.asistencias.util.FormularioUtil.txtMaxLenFormat()");
            String newText = t.getControlNewText();
            if(newText.matches(exp)){
                return t;
            }
            return null;
        };
        tf.setTextFormatter(new TextFormatter<String>(limite));
    }
}
