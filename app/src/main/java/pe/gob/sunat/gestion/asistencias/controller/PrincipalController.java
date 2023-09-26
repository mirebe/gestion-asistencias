/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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
    private void onClickVistaPropietario(){
        System.out.println("onClickVistaPropietario()");
        try {
            FXMLArchivo<GestionPropietariosController> fxPropietario = new FXMLArchivo<>("VistaGestionPropietarios");
            extenderPanel(fxPropietario.getRoot());
            panelContenido.getChildren().clear();
            panelContenido.getChildren().add(fxPropietario.getRoot());
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void onClickVistaEventos(){
        System.out.println("onClickVistaEventos()");
        try {
            FXMLArchivo<GestionEventosController> fxEventos = new FXMLArchivo<>("VistaGestionEventos");
            extenderPanel(fxEventos.getRoot());
            panelContenido.getChildren().clear();
            panelContenido.getChildren().add(fxEventos.getRoot());
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void onClickVistaDashBoard(){
        System.out.println("onClickVistaDashBoard()");
        try {
            FXMLArchivo<DashboardController> fxDashBoard = new FXMLArchivo<>("VistaDashboard");
            extenderPanel(fxDashBoard.getRoot());
            panelContenido.getChildren().clear();
            panelContenido.getChildren().add(fxDashBoard.getRoot());
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void onClickVistaReporteAsistencia(){
        System.out.println("onClickVistaReporteAsistencia()");
        try {
            FXMLArchivo<ReporteAsistenciasController> fxRepAsistencia = new FXMLArchivo<>("VistaReporteAsistencia");
            extenderPanel(fxRepAsistencia.getRoot());
            panelContenido.getChildren().clear();
            panelContenido.getChildren().add(fxRepAsistencia.getRoot());
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void extenderPanel(Parent root){
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
    }
    
    void init(Stage stageVentanaLogin,Stage stageVentanaPrincipal, LoginController loginController, Usuario usuario) {
        this.loginController = loginController;
        this.stageVentanaLogin = stageVentanaLogin;
        this.stageVentanaPrincipal = stageVentanaPrincipal;
        this.usuario = usuario;
        lblUsuario.setText("Bienvenido: "+usuario.getNombres());
        onClickVistaDashBoard();
    }
    
}
