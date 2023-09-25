/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;  
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart; 
import javafx.scene.layout.Pane;

/**
 *
 * @author mireb
 */
public class DashboardController implements Initializable{

    @FXML
    private Pane paneview;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadData();
        
        }
    
    private void loadData() {
        paneview.getChildren().clear();
      ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
         list.add(new PieChart.Data("ASISTENTES", 15));
         list.add(new PieChart.Data("NO ASISTIERON", 5));
 
         PieChart listArr = new PieChart(list);
         listArr.setTitle("ASISTENTES");
         
         paneview.getChildren().add(listArr);
    }
       
     
        
}
