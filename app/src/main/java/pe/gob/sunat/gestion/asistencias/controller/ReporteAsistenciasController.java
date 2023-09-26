/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import pe.gob.sunat.gestion.asistencias.model.entities.Asistencia;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;
import pe.gob.sunat.gestion.asistencias.service.AsistenciaService;
import pe.gob.sunat.gestion.asistencias.service.EventoService;
import pe.gob.sunat.gestion.asistencias.service.PropietarioService;
import pe.gob.sunat.gestion.asistencias.service.impl.AsistenciaServiceImpl;
import pe.gob.sunat.gestion.asistencias.service.impl.EventoServiceImpl;
import pe.gob.sunat.gestion.asistencias.service.impl.PropietarioServiceImpl;
import pe.gob.sunat.gestion.asistencias.util.FXMLArchivo;

/**
 *
 * @author mireb
 */
public class ReporteAsistenciasController implements Initializable{
    @FXML
    private AnchorPane contentMain;
    @FXML
    private ComboBox<Evento> cboEvento;
    @FXML
    private ComboBox<Propietario>  cboPropietario;
    
    @FXML
    private TableView<Asistencia> tablaAsistencias;
    
    @FXML
    private TableColumn<Asistencia, String> idColumn, eventoColumn, propietarioColumn, fechaColumn, editCol;
    
    FXMLArchivo<RegistroAsistenciaController> fxRegistroAsistencia;
    
    @FXML
    private void onClick_Buscar(){
        Long idEvento = null;
        Long idPropietario = null;
        if(cboEvento.getSelectionModel().getSelectedItem()!=null){
            idEvento = cboEvento.getSelectionModel().getSelectedItem().getIdEvento();
        }
        if(cboPropietario.getSelectionModel().getSelectedItem()!=null){
            idPropietario = cboPropietario.getSelectionModel().getSelectedItem().getIdPropietario();
        }
        
        try {
            List<Asistencia> list = asistenciaService.listarAsistencias(idEvento, idPropietario);
            llenarDatosEnTabla(list);
        } catch (Exception ex) {
            //Logger.getLogger(ReporteAsistenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void onClick_Limpiar(){
         cboEvento.getSelectionModel().clearSelection();
         cboPropietario.getSelectionModel().clearSelection();
         try {
            List<Asistencia> list = asistenciaService.listarAsistencias(null, null);
            llenarDatosEnTabla(list);
        } catch (Exception ex) {
            //Logger.getLogger(ReporteAsistenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ObservableList<Asistencia> asistenciaData = FXCollections.observableArrayList();
    private ObservableList<Evento> itemsComboEvento = FXCollections.observableArrayList(); 
    private ObservableList<Propietario> itemsComboPropietario = FXCollections.observableArrayList(); 
    private AsistenciaService asistenciaService;
    private EventoService eventosService;
    private PropietarioService propietarioService;

    public ReporteAsistenciasController() {
        this.asistenciaService = new AsistenciaServiceImpl();
        this.eventosService= new EventoServiceImpl();
        this.propietarioService = new PropietarioServiceImpl();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("pe.gob.sunat.gestion.asistencias.controller.ReporteAsistenciasController.<init>()");
        try {
            fxRegistroAsistencia = new FXMLArchivo<>("VistaRegistroAsistencia");
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        configureCollumns();
        cargarTabla();
        List<Evento> listaEvento;
        List<Propietario> listaPropietario;
        try {
            listaEvento = eventosService.listarEventosActivos();
            listaPropietario = propietarioService.listarPropietario();
            itemsComboEvento.addAll(listaEvento);
            itemsComboPropietario.addAll(listaPropietario);
            cboEvento.setItems(itemsComboEvento);
            cboPropietario.setItems(itemsComboPropietario);
        } catch (Exception ex) {
            Logger.getLogger(RegistroMarcadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void configureCollumns() {
        //tablaGestionEventos.setSelectionModel(null);
	idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getIdAsistencia().toString()));
        eventoColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEvento().getDescripcion()));
        propietarioColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPropietario().getNombresCompleto()));
        fechaColumn.setCellValueFactory((cellData) ->{
            String fechHora = cellData.getValue().getFechaHoraAsistencia().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
            return new SimpleObjectProperty<>(fechHora);
        } );
        Callback<TableColumn<Asistencia, String>, TableCell<Asistencia, String>> cellFoctory = (TableColumn<Asistencia, String> param) -> {
            // make cell containing buttons
            final TableCell<Asistencia, String> cell;
            cell = new TableCell<Asistencia, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        FontAwesomeIcon fntIconEdit = new FontAwesomeIcon();
                        fntIconEdit.setIconName("EDIT");
                        fntIconEdit.setSize("1.8em");
                        
                        Label VerIcon = new Label("");
                        VerIcon.setGraphic(fntIconEdit);
                        VerIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.err.println("EDITTT");
                            if(tablaAsistencias.getSelectionModel()!=null){
                                Asistencia asistencia = tablaAsistencias.getSelectionModel().getSelectedItem();
                                fxRegistroAsistencia.getController().open(contentMain,asistencia);
                            }  
                        });

                        HBox managebtn = new HBox(VerIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(VerIcon, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }

            };

            return cell;
        };
        editCol.setCellFactory(cellFoctory);
    }
    
    public void llenarDatosEnTabla(List<Asistencia> list) {
        try {
            asistenciaData = FXCollections.observableArrayList();
            tablaAsistencias.setItems(FXCollections.observableArrayList(new ArrayList<>()));
            asistenciaData = FXCollections.observableArrayList();
            if(list != null){
                for (Asistencia asistencia : list) {
                    asistenciaData.add(asistencia);
                }
                tablaAsistencias.setItems(asistenciaData);
            }
        } catch (Exception e) {
            System.out.println("Error llenarDatosEnTabla =" + e.getMessage());
            e.printStackTrace();
        }
    }

    void cargarTabla() {
        try {
            List<Asistencia> list = asistenciaService.listarAsistencias(null, null);
            llenarDatosEnTabla(list);
        } catch (Exception ex) {
            //Logger.getLogger(ReporteAsistenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
