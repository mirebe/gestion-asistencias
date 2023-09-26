/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;
import pe.gob.sunat.gestion.asistencias.service.PropietarioService;
import pe.gob.sunat.gestion.asistencias.service.impl.PropietarioServiceImpl;
import pe.gob.sunat.gestion.asistencias.util.FormularioUtil;

/**
 *
 * @author mireb
 */
public class RegistroPropietariosController implements Initializable{
    private AnchorPane parent;
    private GestionPropietariosController gestionpropietariosController;
    private Propietario propietario;
    private PropietarioService propietarioService;
    
    @FXML
    private TextField txtPaterno, txtMaterno, txtNombre, txtDni, txtCorreo,txtDpto;
    @FXML
    private RadioButton rbMasculino, rbFemenino ;
    
    private String tipoOperacion = "INS" ; //INS,UPD
    
    @FXML
    private AnchorPane contenMain;
    
    public RegistroPropietariosController() {
        propietarioService = new PropietarioServiceImpl();
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
        if(txtMaterno.getText().length()==0){
            mostrarAlertas("Warning", "Ingrese apellido Materno", Alert.AlertType.WARNING);
            return;
        }
        if(txtPaterno.getText().length()==0){
            mostrarAlertas("Warning", "Ingrese apellido Paterno", Alert.AlertType.WARNING);
            return;
        }
        if(txtNombre.getText().length()==0){
            mostrarAlertas("Warning", "Ingrese el Nombre", Alert.AlertType.WARNING);
            return;
        }
        if(txtDni.getText().length()==0){
            mostrarAlertas("Warning", "Ingrese su numero de DNI", Alert.AlertType.WARNING);
            return;
        }
        if(!rbMasculino.isSelected() && !rbFemenino.isSelected() ){
            mostrarAlertas("Warning", "Sleccione su Sexo.", Alert.AlertType.WARNING);
            return;
        }
        
        Propietario pro = new Propietario();
        pro.setApellidoMaterno(txtMaterno.getText());
        pro.setApellidoPaterno(txtPaterno.getText());
        pro.setCorreo(txtCorreo.getText());
        pro.setDni(txtDni.getText());
        pro.setDpto( txtDpto.getText().length()==0?0:Integer.valueOf(txtDpto.getText()) );
        pro.setEstado(1);
        pro.setIdGenero(rbMasculino.isSelected()?1:2);
        pro.setNombres(txtNombre.getText());
        
        try {
            if(tipoOperacion.equals("INS") ){
                propietarioService.guardar(pro);
                mostrarAlertas("Informacion", "Se agrego un nuevo propietario exitosamente", Alert.AlertType.INFORMATION);
            }else{
                pro.setIdPropietario(propietario.getIdPropietario());
             
                propietarioService.actualizar(pro);
                 mostrarAlertas("Informacion", "Se actualizo el propietario exitosamente", Alert.AlertType.INFORMATION);
            }
            
            if(parent != null){
                parent.getChildren().remove(contenMain);
            }
            gestionpropietariosController.llenarDatosEnTabla(propietarioService.listarPropietario());
            
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlertas("ERROR", "Excepcion="+ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    void open(AnchorPane contentMain, Propietario propietario, GestionPropietariosController aThis) {
        this.parent = contentMain;
        this.gestionpropietariosController = aThis;
        AnchorPane.setBottomAnchor(contenMain, 0.0);
	AnchorPane.setTopAnchor(contenMain, 0.0);
	AnchorPane.setLeftAnchor(contenMain, 0.0);
	AnchorPane.setRightAnchor(contenMain, 0.0);
        
        System.out.println("::::: Open mensaje ");
        if(contentMain != null){
            contentMain.getChildren().add(contenMain);
            asignarValoresIniciales( propietario);
        }
        this.propietario = propietario;
        if(propietario.getIdPropietario()!=null && propietario.getIdPropietario().intValue()>0){
            tipoOperacion = "UPD";
        }
        
        System.out.println("::::: Open mensaje ");
    }
    
    private void asignarValoresIniciales(Propietario pro){
        //txtPaterno, txtMaterno, txtNombre, txtDni, txtCorreo,txtDpto
        
        txtPaterno.setText(pro.getApellidoMaterno()==null?"":pro.getApellidoMaterno());
        txtMaterno.setText(pro.getApellidoPaterno()==null?"":pro.getApellidoPaterno());
        txtNombre.setText(pro.getNombres()==null?"":pro.getNombres());
        txtDni.setText(pro.getDni()==null?"":pro.getDni());
        txtCorreo.setText(pro.getCorreo()==null?"":pro.getCorreo());
        txtDpto.setText(pro.getDpto()==null?"":""+pro.getDpto());

        if(pro.getIdGenero()!= null){
            if(pro.getIdGenero()==1){
                rbMasculino.setSelected(true);
            }else{
                rbFemenino.setSelected(true);
            }
        }else{
            rbMasculino.setSelected(false);
            rbFemenino.setSelected(false);
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rbMasculino.setToggleGroup(toggleGroup);
        rbFemenino.setToggleGroup(toggleGroup);
        //txtPaterno, txtMaterno, txtNombre, txtDni, txtCorreo,txtDpto
                
        FormularioUtil.txtMaxLenFormat(txtPaterno, "[A-Za-z\\d]{0,40}$");
        FormularioUtil.txtMaxLenFormat(txtMaterno, "[A-Za-z\\d]{0,40}$");
        FormularioUtil.txtMaxLenFormat(txtNombre, "[A-Za-z\\d]{0,40}$");
        FormularioUtil.txtMaxLenFormat(txtDni, "[\\d]{0,8}$");
        FormularioUtil.txtMaxLenFormat(txtDpto, "^[0-9]{0,5}");
        //FormularioUtil.txtMaxLenFormat(txtDpto, "^[A-Za-z0-9\\s]{0,15}");
        //FormularioUtil.txtMaxLenFormat(txtCorreo, "");
    }
    
    private void mostrarAlertas(String header, String content, Alert.AlertType type) {
        Alert dialogo = new Alert(type);
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.show();
    }
}
