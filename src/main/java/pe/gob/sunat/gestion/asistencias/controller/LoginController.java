package pe.gob.sunat.gestion.asistencias.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pe.gob.sunat.gestion.asistencias.App;
import pe.gob.sunat.gestion.asistencias.model.entities.Usuario;
import pe.gob.sunat.gestion.asistencias.service.LoginService;
import pe.gob.sunat.gestion.asistencias.service.impl.LoginServiceImpl;
import pe.gob.sunat.gestion.asistencias.util.FXMLArchivo;
import pe.gob.sunat.gestion.asistencias.util.IconosAwesome;

/**
 *
 * @author mireb
 */
public class LoginController implements Initializable{
    private Stage stageVentanaLogin;
    private Scene esceneMainLogin;
    
    @FXML
    TextField txtUsuario;
    @FXML
    PasswordField txtPassword;
    @FXML
    ComboBox<String> cboTipoIngreso;
    @FXML
    AnchorPane contentMain;
    
    FXMLArchivo<DialogoMensajeController> fxDialogo;
    private final LoginService loginService;
    ObservableList<String> itemsTipoIngreso = FXCollections.observableArrayList(); 
    
    public LoginController() {
        System.out.println("LoginController.<constructor>()"); 
        loginService = new LoginServiceImpl();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //invoca despues del contructor ya se encuentra asignado valores de javaFX
        System.out.println("LoginController.<init>()");
        itemsTipoIngreso.addAll("Administracion","Registro");
        cboTipoIngreso.setItems(itemsTipoIngreso);
        try {
            fxDialogo = new FXMLArchivo<>("VistaDialogoMensaje");
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void onAction_botonIngresar(){
        Usuario usuario = new Usuario();
        usuario.setUsuario(txtUsuario.getText());
        usuario.setClave(txtPassword.getText());
        
        System.err.println("--->"+cboTipoIngreso.getValue());
        
        Map<String,Object> result;
        try {
            result = loginService.validarDatosLogin(usuario, new Integer[]{1,2,3});
            if(Boolean.parseBoolean(result.get("STATUS").toString())){
                System.out.println("pe.gob.sunat.gestion.asistencias.controller.LoginController.onAction_botonIngresar()");
                if(cboTipoIngreso.getValue()== null){
                    mostrarMensaje("Seleccione modo ingreso", "Mensaje", IconosAwesome.ERROR);
                }else{
                    if(cboTipoIngreso.getValue().equals("Administracion")){
                        showVentanaPrincipal((Usuario)result.get("usuario")); //validado correctamente 
                     }else{
                         showVentanaRegistroMarcador();
                     }
                }
                
            }else{
                System.out.println("No ingresa");
                mostrarMensaje(result.get("MSG").toString(), result.get("CAMPO").toString(), IconosAwesome.ERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarMensaje("Ocurrio error ..", "Mensaje Error", IconosAwesome.ERROR);
        }
        
    }
    
    @FXML
    private void onClicked_lblSalir(MouseEvent event) {
    	System.exit(0);
    }
    
   
    private void mostrarMensaje(String mensaje, String titulo, IconosAwesome icono) {
        fxDialogo.getController().AsignarValores(titulo, mensaje, icono);
        fxDialogo.getController().open(contentMain);
    }
    
    public void init(Stage stage, Scene scene) {
        this.stageVentanaLogin = stage;
        this.esceneMainLogin = scene;
    }

    private void showVentanaPrincipal(Usuario usuario){
        Platform.runLater(() -> {
            try {
                FXMLArchivo<PrincipalController> fxmlPrincipal = new FXMLArchivo<>("VistaPrincipal");
                Scene scene = new Scene(fxmlPrincipal.getRoot());
                Stage stageVentanaPrincipal = new Stage();
                stageVentanaPrincipal.setTitle("SISTEMA DE CONTROL DE ASISTENCIA VER 1.0");
                stageVentanaPrincipal.setScene(scene);
                
                fxmlPrincipal.getController().init(stageVentanaLogin,stageVentanaPrincipal,this,usuario);
                
                stageVentanaLogin.close();
                stageVentanaPrincipal.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void showVentanaLogin() {
        txtUsuario.clear();
        txtPassword.clear();
        stageVentanaLogin.show();
        
    }

    private void showVentanaRegistroMarcador() {
        Platform.runLater(() -> {
            try {
                FXMLArchivo<RegistroMarcadorController> fxmlRegistroMarcacion = new FXMLArchivo<>("VistaRegistroMarcador");
                Scene scene = new Scene(fxmlRegistroMarcacion.getRoot());
                Stage stageVentanaRegistroMarcacion = new Stage();
                stageVentanaRegistroMarcacion.setScene(scene);
                stageVentanaRegistroMarcacion.initStyle(StageStyle.UNDECORATED);
                fxmlRegistroMarcacion.getController().init(stageVentanaLogin,stageVentanaRegistroMarcacion,this);
                
                stageVentanaLogin.close();
                stageVentanaRegistroMarcacion.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
