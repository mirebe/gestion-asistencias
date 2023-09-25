/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;
import pe.gob.sunat.gestion.asistencias.service.EventoService;
import pe.gob.sunat.gestion.asistencias.service.impl.EventoServiceImpl;
import pe.gob.sunat.gestion.asistencias.util.EventoProperty;
import pe.gob.sunat.gestion.asistencias.util.FXMLArchivo;

/**
 *
 * @author mireb
 */
public class GestionEventosController implements Initializable {
    @FXML
    AnchorPane contentMain;
    
    @FXML
    private TextField txtNombreEvento;
    
    @FXML
    private TableView<EventoProperty> tablaGestionEventos;

    @FXML
    private TableColumn<EventoProperty, String> idColumn,nombreColumn, fechaColumn, horaColumn, estadoColumn, editCol;
          
    private ObservableList<EventoProperty> eventoData = FXCollections.observableArrayList();
    
    FXMLArchivo<DialogoMensajeController> fxDialogo;
    FXMLArchivo<RegistroEventosController> fxRegistroEventos;
    private GestionEventosController gestion = this;
   
    private final EventoService eventoService;
    
    public GestionEventosController() {
        System.out.println("GestionEventosController.<constructor>()");
        eventoService = new EventoServiceImpl();
    }
    
    @FXML
    private void onClick_btnAgregar(){
        fxRegistroEventos.getController().open(contentMain,new Evento(),this);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fxDialogo = new FXMLArchivo<>("VistaDialogoMensaje");
            fxRegistroEventos = new FXMLArchivo<>("VistaRegistroEventos");
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        configureCollumns();
        llenarDatosEnTabla();
        
        buscarConEnter();
    }
    
    public void buscarConEnter(){
        txtNombreEvento.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    System.out.println("Buscar");
                    if(txtNombreEvento.getText().length()==0){
                        llenarDatosEnTabla();
                    }
                    if(txtNombreEvento.getText().length()< 3){
                        return;
                    }
                    try {
                        List<Evento> list = eventoService.listarEventoxNombre(txtNombreEvento.getText());
                        tablaGestionEventos.setItems(FXCollections.observableArrayList(new ArrayList<>()));
                        eventoData = FXCollections.observableArrayList();

                        if(list != null){
                            for (Evento evento : list) {
                                EventoProperty ev = new EventoProperty(evento.getIdEvento(), evento.getDescripcion(), evento.getFechaEvento(), evento.getHoraEvento(), evento.getEstado());
                                eventoData.add(ev);
                            }
                            tablaGestionEventos.setItems(eventoData);
                        }
                    } catch (Exception e) {
                        System.out.println("Error llenarDatosEnTabla =" + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });
    } 
   
    private void configureCollumns() {
        //tablaGestionEventos.setSelectionModel(null);
	idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getIdEvento()));
        nombreColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescripcion()));
        fechaColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaEvento()));
        horaColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHoraEvento()));
        estadoColumn.setCellValueFactory(cellData -> {
            String res = "BAJA";
            if(cellData.getValue().getEstado().equals("1")){
                res = "ACTIVO";
            }
            return new SimpleObjectProperty<>(res);
        });
        
         //add cell of button edit 
         Callback<TableColumn<EventoProperty, String>, TableCell<EventoProperty, String>> cellFoctory = (TableColumn<EventoProperty, String> param) -> {
            // make cell containing buttons
            final TableCell<EventoProperty, String> cell;
            cell = new TableCell<EventoProperty, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Label deleteIcon = new Label("Eliminar");
                        Label editIcon = new Label("Editar");
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            if(tablaGestionEventos.getSelectionModel()!=null){
                                EventoProperty evento = tablaGestionEventos.getSelectionModel().getSelectedItem();
                                try {
                                    eventoService.desactivarEvento(Long.valueOf(evento.getIdEvento()));
                                    mostrarAlertas("Informacion", "Evento desactivado!", Alert.AlertType.INFORMATION);
                                    llenarDatosEnTabla();
                                } catch (Exception e) {
                                }
                            }   
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.err.println("EDITTT");
                            if(tablaGestionEventos.getSelectionModel()!=null){
                                EventoProperty evento = tablaGestionEventos.getSelectionModel().getSelectedItem();
                                fxRegistroEventos.getController().open(contentMain,evento.getEvento(),gestion);
                            }  
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
    }
  
  
    public void llenarDatosEnTabla() {
        try {
            List<Evento> list = eventoService.listarEvento();
            tablaGestionEventos.setItems(FXCollections.observableArrayList(new ArrayList<>()));
            eventoData = FXCollections.observableArrayList();
            
            if(list != null){
                for (Evento evento : list) {
                    EventoProperty ev = new EventoProperty(evento.getIdEvento(), evento.getDescripcion(), evento.getFechaEvento(), evento.getHoraEvento(), evento.getEstado());
                    eventoData.add(ev);
                }
                tablaGestionEventos.setItems(eventoData);
            }
        } catch (Exception e) {
            System.out.println("Error llenarDatosEnTabla =" + e.getMessage());
            e.printStackTrace();
        }
    }

    
    private void mostrarAlertas(String header, String content, Alert.AlertType type) {
        Alert dialogo = new Alert(type);
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.show();
    }
   
}
