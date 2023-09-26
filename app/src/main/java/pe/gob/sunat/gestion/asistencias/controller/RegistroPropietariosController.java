/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;
import pe.gob.sunat.gestion.asistencias.service.PropietarioService;
import pe.gob.sunat.gestion.asistencias.service.impl.PropietarioServiceImpl;

/**
 *
 * @author mireb
 */
public class RegistroPropietariosController implements Initializable {

    private AnchorPane parent;
    private GestionPropietariosController gestionPropietariosController;
    private Propietario propietario;
    private PropietarioService propietarioService;
    private String tipoOperacion = "INS";

    @FXML
    private AnchorPane contenMain;
    
    
    @FXML 
    private TextField txtDNI,txtCorreo,txtNombres,txtApellido1,txtApellido2,txtDepartamento ;
    
    @FXML RadioButton rbMasculino, rbFemenino, rbActivo, rbBaja; 
    
    

    public RegistroPropietariosController() {
        propietarioService = new PropietarioServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cargarCombos();
    }
    
    
    @FXML
    private void onClick_btnSalir(){
        if(parent != null){
            parent.getChildren().remove(contenMain);
        }
    }
    
    @FXML
    private void onActionCancelar(){
        if(parent != null){
            parent.getChildren().remove(contenMain);
        }
    }
    
     @FXML
    private void onActionAceptar(){
        
    }
    

    public void open(AnchorPane contentMain, Propietario propietario, GestionPropietariosController gestionPropietariosController) {
        System.out.println("pe.gob.sunat.gestion.asistencias.controller.RegistroPropietariosController.open()");
        this.parent = contentMain;
        this.gestionPropietariosController = gestionPropietariosController;
        AnchorPane.setBottomAnchor(contenMain, 0.0);
        AnchorPane.setTopAnchor(contenMain, 0.0);
        AnchorPane.setLeftAnchor(contenMain, 0.0);
        AnchorPane.setRightAnchor(contenMain, 0.0);

        System.out.println("::::: Open mensaje ");
        if (contentMain != null) {
            contentMain.getChildren().add(contenMain);
            asignarValoresIniciales(propietario);
        }
        this.propietario = propietario;
        if (propietario.getIdPropietario() != null && propietario.getIdPropietario() > 0) {
            tipoOperacion = "UPD";
        }

        System.out.println("::::: Open mensaje ");
    }

    private void asignarValoresIniciales(Propietario propietario) {
        txtDNI.setText(propietario.getDni());
        txtCorreo.setText(propietario.getCorreo());
        txtNombres.setText(propietario.getNombres());
        txtApellido1.setText(propietario.getApellidoPaterno());
        txtApellido2.setText(propietario.getApellidoMaterno());
        txtDepartamento.setText(propietario.getDpto().toString());
        txtDepartamento.setText(propietario.getDpto().toString());
        if(propietario.getEstado().equals(1)){
                rbMasculino.setSelected(true);
            }else{
                rbFemenino.setSelected(true);
        }
        if(propietario.getEstado().equals(1)){
                rbActivo.setSelected(true);
            }else{
                rbBaja.setSelected(true);
        }
    }
    

     private void cargarCombos() {

    }
}
