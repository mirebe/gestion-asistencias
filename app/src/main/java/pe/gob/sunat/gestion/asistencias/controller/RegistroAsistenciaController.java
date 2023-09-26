/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import pe.gob.sunat.gestion.asistencias.model.entities.Asistencia;

/**
 *
 * @author mireb
 */
public class RegistroAsistenciaController implements Initializable{    
    private AnchorPane parent;
    @FXML
    private AnchorPane contenMain;
    @FXML
    private Label lblEvento,lblHoraEvento,lblPropietario,lblDni,lblHoraIngreso;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void onClick_Cancelar(){
        if(parent != null){
            parent.getChildren().remove(contenMain);
        }
    }
    
    @FXML
    private void onClick_btnSalir(){
        if(parent != null){
            parent.getChildren().remove(contenMain);
        }
    }
    
    
    
    void open(AnchorPane contentMain, Asistencia asistencia) {
        this.parent = contentMain;
        AnchorPane.setBottomAnchor(contenMain, 0.0);
	AnchorPane.setTopAnchor(contenMain, 0.0);
	AnchorPane.setLeftAnchor(contenMain, 0.0);
	AnchorPane.setRightAnchor(contenMain, 0.0);
        
        System.out.println("::::: Open mensaje ");
        if(contentMain != null){
             contentMain.getChildren().add(contenMain);
            // asignarValoresIniciales( asistencia);
            lblEvento.setText(asistencia.getEvento().getDescripcion());
           
            lblHoraEvento.setText( asistencia.getEvento().getHoraEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")));
            lblPropietario.setText(asistencia.getPropietario().getNombresCompleto());
            lblDni.setText(asistencia.getPropietario().getDni());
            lblHoraIngreso.setText(asistencia.getFechaHoraAsistencia().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")));

        }
    }
    
}
