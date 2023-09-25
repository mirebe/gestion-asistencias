package pe.gob.sunat.gestion.asistencias.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author mireb
 */
public class RegistroMarcacionController {
    private Stage stageVentanaRegistroMarcacion;
    private Stage stageVentanaLogin;
    private LoginController loginController;
    
    @FXML
    private void onClicked_lblSalir(MouseEvent event) {
    	stageVentanaRegistroMarcacion.close();
        loginController.showVentanaLogin();
    }
    
    void init(Stage stageVentanaLogin, Stage stageVentanaRegistroMarcacion,LoginController loginController) {
        this.loginController = loginController;
        this.stageVentanaLogin = stageVentanaLogin;
        this.stageVentanaRegistroMarcacion = stageVentanaRegistroMarcacion;
    }
    
}
