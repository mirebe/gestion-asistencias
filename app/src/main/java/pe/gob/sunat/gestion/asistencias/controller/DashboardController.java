/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import com.mysql.cj.util.StringUtils;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;  
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart; 
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import pe.gob.sunat.gestion.asistencias.enums.EstadoEnum;
import pe.gob.sunat.gestion.asistencias.model.dao.impl.EventoDaoImpl;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;
import pe.gob.sunat.gestion.asistencias.model.entities.EventoCombo;
import pe.gob.sunat.gestion.asistencias.model.entities.EventoDashboard;
import pe.gob.sunat.gestion.asistencias.model.util.Conexion;
import pe.gob.sunat.gestion.asistencias.service.EventoService;
import pe.gob.sunat.gestion.asistencias.service.impl.EventoServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import pe.gob.sunat.gestion.asistencias.model.entities.AsistenciaEvento;
/**
 *
 * @author mireb
 */
public class DashboardController implements Initializable{

    @FXML
    private Pane paneview;
    
    @FXML
    private ComboBox<EventoCombo> idcomboevento;
    
    private final EventoService eventoService;
    
    private ObservableList<EventoCombo> eventoData = FXCollections.observableArrayList();
   
    public DashboardController() {
        System.out.println("DashboardController.<constructor>()");
        eventoService = new EventoServiceImpl();
    }    
    
    @FXML
    private void cambioCombo() {        
        Long dd = (long)idcomboevento.getSelectionModel().getSelectedItem().getIdEvento();     
        if (dd != 0) {
            System.out.println("combo cambio - "+dd);
            try {
                List<AsistenciaEvento> list = eventoService.listarAsistents(dd);
                System.out.println(" capacidad "+list.get(0).getCapacidad());
                System.out.println("cantidad " + list.get(0).getIdEvento());
                loadData(list.get(0).getIdEvento(),list.get(0).getCapacidad());
            } catch (Exception ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }else{
            paneview.getChildren().clear();
        }      
    } 
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<EventoCombo> list = new ArrayList<EventoCombo>();
            
            //list.add(new EventoCombo(0L,"Seleccione"));
            
            list.addAll(eventoService.listarEventosActivos2());
            if(list.size()>0){
                 eventoData.addAll(list);
                idcomboevento.setItems(eventoData);
                idcomboevento.getSelectionModel().selectFirst();
                cambioCombo();
            }
           

        } catch (Exception ex) {
            Logger.getLogger(DashboardController1.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    
    private void loadData(long cantidad ,Long capacidad) {
        paneview.getChildren().clear();
        Long noAsistente = capacidad - cantidad;
        System.out.println(" noAsistente "+noAsistente);
     
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        list.add(new PieChart.Data("ASISTENTES : "+ cantidad, cantidad));
        list.add(new PieChart.Data("NO ASISTIERON : " + noAsistente, noAsistente));
 
        PieChart listArr = new PieChart(list);
        listArr.setTitle("ASISTENTES");
         
        paneview.getChildren().add(listArr);
    }
       
     
        
}