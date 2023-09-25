/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pe.gob.sunat.gestion.asistencias.model.entities.Usuario;
import pe.gob.sunat.gestion.asistencias.util.FXMLArchivo;

/**
 *
 * @author mireb
 */
public class PrincipalController {
    private Stage stageVentanaLogin;
    private Stage stageVentanaPrincipal;
    private LoginController loginController;
    private Usuario usuario;
    
    @FXML
    Label lblUsuario;
    @FXML
    AnchorPane panelContenido;
    
    @FXML
    private void onClickCerrarSesion(){
        System.out.println("====cerrando ventana principal ===");
        stageVentanaPrincipal.close();
        loginController.showVentanaLogin();
    }
    
    @FXML
    private void onClickVistaTrabajador(){
        System.out.println("onClickVistaTrabajador()");
        try {
            FXMLArchivo<GestionTrabajadoresController> fxTrabajador = new FXMLArchivo<>("VistaGestionTrabajadores");
            AnchorPane.setBottomAnchor(fxTrabajador.getRoot(), 0.0);
            AnchorPane.setTopAnchor(fxTrabajador.getRoot(), 0.0);
            AnchorPane.setLeftAnchor(fxTrabajador.getRoot(), 0.0);
            AnchorPane.setRightAnchor(fxTrabajador.getRoot(), 0.0);
        
            panelContenido.getChildren().clear();
            panelContenido.getChildren().add(fxTrabajador.getRoot());
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void init(Stage stageVentanaLogin,Stage stageVentanaPrincipal, LoginController loginController, Usuario usuario) {
        this.loginController = loginController;
        this.stageVentanaLogin = stageVentanaLogin;
        this.stageVentanaPrincipal = stageVentanaPrincipal;
        this.usuario = usuario;
        lblUsuario.setText("Bienvenido: "+usuario.getNombre());
    }
    
}
