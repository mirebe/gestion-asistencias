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
import javafx.beans.property.SimpleStringProperty;
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
    
    @FXML
    TextField txtbusqueda;    
     
     @FXML
    RadioButton  r1;  
      @FXML
    RadioButton  r2;     
      @FXML
    RadioButton  r3;
      
     @FXML
    private TableView<EventoDashboard> dasboardTable;
  
    @FXML
    private TableColumn<EventoDashboard, Long> idColumn;

    @FXML
    private TableColumn<EventoDashboard, String> propietarioColumn;

    @FXML
    private TableColumn<EventoDashboard, String> eventoColumn;

    @FXML
    private TableColumn<EventoDashboard, String> condominioColumn;

    @FXML
    private TableColumn<EventoDashboard, LocalDateTime> fechahoraColumn;
      
    private ObservableList<EventoDashboard> eventoData2 = FXCollections.observableArrayList();
    
    private final DateTimeFormatter dtff = DateTimeFormatter.ofPattern("HH:mm");

    public DashboardController() {
        System.out.println("DashboardController.<constructor>()");
        eventoService = new EventoServiceImpl();
    }    
    
    private void llenarDatosEnTabla(int tipoC, String txtbusqueda, Long idEvento) {
        try {
            List<EventoDashboard> list = eventoService.listarEventoDashboard(tipoC,txtbusqueda,idEvento);
            eventoData2.addAll(list);
        } catch (Exception e) {
            System.out.println("Error llenarDatosEnTabla =" + e.getMessage());
            e.printStackTrace();
        }
    }
    

    
    private void enlazarTabla() {
        dasboardTable.setItems(eventoData2);
  //      txtNombreEvento.textProperty().bindBidirectional(eventoActual.getDescripcion1());
  //      datePikerFechaEvento.valueProperty().bindBidirectional(eventoActual.getFechaEvento1());
//        String horaMinutoData = dtf.format(eventoActual.getHoraEvento());
//        comboHora.valueProperty().bindBidirectional(new SimpleObjectProperty(obtenerHora(horaMinutoData)));
//        comboMinuto.valueProperty().bindBidirectional(new SimpleObjectProperty(obtenerMinuto(horaMinutoData)));
//        comboSiglas.valueProperty().bindBidirectional(new SimpleObjectProperty(obtenerMarcador(horaMinutoData)));


        idColumn.setCellValueFactory(rowData -> rowData.getValue().getIdEvento());
        propietarioColumn.setCellValueFactory(rowData -> rowData.getValue().getNombres());
        eventoColumn.setCellValueFactory(rowData -> rowData.getValue().getDescripcion());
        fechahoraColumn.setCellValueFactory(rowData ->  rowData.getValue().getFechahora());
        condominioColumn.setCellValueFactory(rowData ->  rowData.getValue().getNombreCondominio());
        //estadoColumn.setCellValueFactory(rowData -> new SimpleStringProperty(EstadoEnum.getStringValueFromInt(rowData.getValue().getEstado())));
    }
    
    
    public void onCambio(ActionEvent event) {
        
        if(r1.isSelected()) {
            txtbusqueda.setPromptText("Ingrese apellidos y nombres del propietario");
        }else if(r2.isSelected()) {
            txtbusqueda.setPromptText("Ingrese DNI del propietario");
        }if(r3.isSelected()) {
             txtbusqueda.setPromptText("Ingrese ID del propietario");
        }
        
    }
   
    private void mostrarAlertas(String header, String content, Alert.AlertType type) {
        Alert dialogo = new Alert(type);
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.show();
    }    
    
    public void cambioCombo(ActionEvent event) {
        
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
    
    public void BotonConsultar(){
        
        dasboardTable.getItems().clear();
        System.out.println("combo " + idcomboevento);
        System.out.println("combo " + idcomboevento.getValue().toString() );

        if ("Seleccione".equals(idcomboevento.getValue())) {
            
            mostrarAlertas("Warning", "Seleccione un evento ", Alert.AlertType.WARNING);
           return;
        }
        
        
        Long opcSeleccionada = idcomboevento.getSelectionModel().getSelectedItem().getIdEvento();

         System.out.println("datos " + txtbusqueda.getText());
         System.out.println("opcSeleccionada " + opcSeleccionada);

        if (!r1.isSelected() && !r2.isSelected() && !r3.isSelected() ) {
            mostrarAlertas("Warning", "Seleccione un tipo de busqueda ", Alert.AlertType.WARNING);
           return;
        }


        if (txtbusqueda.getText().isEmpty() ) {
            mostrarAlertas("Warning", "Ingrese un criterio de busqueda", Alert.AlertType.WARNING);
           return;
        }

         if(r1.isSelected()) { // 1
             System.out.println("tipo busqueda nombres " );
             llenarDatosEnTabla(1,txtbusqueda.getText(),opcSeleccionada);
         }else if(r2.isSelected()) { // 2
             System.out.println("tipo busqueda dni " );
             llenarDatosEnTabla(2,txtbusqueda.getText(),opcSeleccionada);
         }if(r3.isSelected()) { // 3
              System.out.println("tipo busqueda id " );
              llenarDatosEnTabla(3,txtbusqueda.getText(),opcSeleccionada);
         }   

         enlazarTabla();
        
        
        
        
 
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<EventoCombo> list = new ArrayList<EventoCombo>();
            list.add(new EventoCombo(0L,"Seleccione"));
            
            list.addAll(eventoService.listarEventosActivos2());
            
            eventoData.addAll(list);
            idcomboevento.setItems(eventoData);
            idcomboevento.getSelectionModel().selectFirst();

        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
