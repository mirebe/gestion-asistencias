/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;
import pe.gob.sunat.gestion.asistencias.service.PropietarioService;
import pe.gob.sunat.gestion.asistencias.service.impl.PropietarioServiceImpl;
import pe.gob.sunat.gestion.asistencias.util.FXMLArchivo;


/**
 *
 * @author mireb
 */
public class GestionPropietariosController implements Initializable {
    @FXML
    AnchorPane contentMain;
    
    @FXML
    private TextField txtNombrePropietario;
    
    @FXML
    private TableView<Propietario> tablaGestionPropietarios;

    @FXML
    private TableColumn<Propietario, String> idColumn,dniColumn, nombreColumn, dptoColumn, estadoColumn,  editCol;
     
     private ObservableList<Propietario> propietarioData = FXCollections.observableArrayList();
    
    FXMLArchivo<DialogoMensajeController> fxDialogo;
    FXMLArchivo<RegistroPropietariosController> fxRegistroPropietarios;
    private GestionPropietariosController gestion = this;
    
    private PropietarioService propietarioService;

    public GestionPropietariosController() {
        propietarioService = new PropietarioServiceImpl();
    }
    
     @FXML
    private void onClick_btnAgregar(){
        fxRegistroPropietarios.getController().open(contentMain,new Propietario(),this);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            fxDialogo = new FXMLArchivo<>("VistaDialogoMensaje");
            fxRegistroPropietarios = new FXMLArchivo<>("VistaRegistroPropietarios");
            
            configureCollumns();
            llenarDatosEnTabla(propietarioService.listarPropietario());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        buscarConEnter();
    }
    
    public void buscarConEnter(){
        txtNombrePropietario.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    System.out.println("Buscar");
                     try {
                        if(txtNombrePropietario.getText().length()==0){
                            llenarDatosEnTabla(propietarioService.listarPropietario());
                        }
                        if(txtNombrePropietario.getText().length()< 3){
                            return;
                        }
                        List<Propietario> list = propietarioService.listarPropietarioxNombre(txtNombrePropietario.getText());
                        llenarDatosEnTabla(list);
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
       // idColumn,dniColumn, nombreColumn, dptoColumn,  editCol
	idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getIdPropietario().toString()));
        dniColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDni()));
        nombreColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombresCompleto()));
        dptoColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDpto().toString()));
        estadoColumn.setCellValueFactory(cellData -> {
            String res = "ACTIVO";
            System.out.println("estadooooooo======0"+cellData.getValue().getEstado());
            if(cellData.getValue().getEstado()==0){
                res = "BAJA";
            }
            return new SimpleObjectProperty<>(res);
        });
        dptoColumn.setStyle( "-fx-alignment: CENTER;");
        estadoColumn.setStyle( "-fx-alignment: CENTER;");
        
        
         //add cell of button edit 
         Callback<TableColumn<Propietario, String>, TableCell<Propietario, String>> cellFoctory = (TableColumn<Propietario, String> param) -> {
            // make cell containing buttons
            final TableCell<Propietario, String> cell;
            cell = new TableCell<Propietario, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        FontAwesomeIcon fntIconDel = new FontAwesomeIcon();
                        fntIconDel.setIconName("CLOSE");
                        fntIconDel.setSize("1.8em");
                                
                        FontAwesomeIcon fntIconEdit = new FontAwesomeIcon();
                        fntIconEdit.setIconName("EDIT");
                        fntIconEdit.setSize("1.8em");
                        
                        Label deleteIcon = new Label("");
                        Label editIcon = new Label("");
                                                
                        deleteIcon.setGraphic(fntIconDel);
                        editIcon.setGraphic(fntIconEdit);   
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            if(tablaGestionPropietarios.getSelectionModel()!=null){
                                Propietario evento = tablaGestionPropietarios.getSelectionModel().getSelectedItem();
                                try {
                                    propietarioService.desactivar(Long.valueOf(evento.getIdPropietario()));
                                    mostrarAlertas("Informacion", "Evento desactivado!", Alert.AlertType.INFORMATION);

                                    llenarDatosEnTabla(propietarioService.listarPropietario());
                                } catch (Exception e) {
                                }
                            }   
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.err.println("EDITTT");
                            if(tablaGestionPropietarios.getSelectionModel()!=null){
                                Propietario propietario = tablaGestionPropietarios.getSelectionModel().getSelectedItem();
                                fxRegistroPropietarios.getController().open(contentMain,propietario,gestion);
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
    
    public void llenarDatosEnTabla(List<Propietario> list) {
        try {
            //List<Propietario> list = propietarioService.listarEvento();
            tablaGestionPropietarios.setItems(FXCollections.observableArrayList(new ArrayList<>()));
            propietarioData = FXCollections.observableArrayList();
            
            if(list != null){
                propietarioData.addAll(list);
                tablaGestionPropietarios.setItems(propietarioData);
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