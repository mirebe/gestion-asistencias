/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import pe.gob.sunat.gestion.asistencias.util.PropitarioProperty;
import pe.gob.sunat.gestion.asistencias.util.FXMLArchivo;
import pe.gob.sunat.gestion.asistencias.util.IconosAwesome;

/**
 *
 * @author mireb
 */
public class GestionPropietariosController implements Initializable {
    @FXML
    private AnchorPane contentMain;

    @FXML
    private TextField txtDNI;

    @FXML
    private TableView<PropitarioProperty> tablaGestionPropietarios;

    @FXML
    private TableColumn<PropitarioProperty, String> idColumn, dniColumn, nombresColumn, dptoColumn, correoColumn, estadoColumn, editCol;

    private ObservableList<PropitarioProperty> propietarioData = FXCollections.observableArrayList();
    
    private ObservableList<PropitarioProperty> propietario_DNI_Data = FXCollections.observableArrayList();

    FXMLArchivo<DialogoMensajeController> fxDialogo;
    FXMLArchivo<RegistroPropietariosController> fxRegistroPropietarios;
    
    private GestionPropietariosController gestion = this;

    private final PropietarioService propietarioService;

    public GestionPropietariosController() {
        System.out.println("GestionPropietariosController.<constructor>()");
        propietarioService = new PropietarioServiceImpl();
    }

    @FXML
    private void onClick_btnAgregar() {
        System.out.println("pe.gob.sunat.gestion.asistencias.controller.GestionPropietariosController.onClick_btnAgregar()");
       fxRegistroPropietarios.getController().open(contentMain,new Propietario(), this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fxDialogo = new FXMLArchivo<>("VistaDialogoMensaje");
            fxRegistroPropietarios = new FXMLArchivo<>("VistaRegistroPropietarios");
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        configureCollumns();
        llenarDatosEnTabla();
        buscarConEnter();
    }

    public void buscarConEnter() {
        txtDNI.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println("Buscar");
                    Propietario proval = new Propietario();
                    proval.setDni(txtDNI.getText().trim());
                    Integer[] valores = new Integer[]{1};
                    Map<String, Object> result;
                    try {
                        result = propietarioService.validarDatosPropietario(proval, valores);
                        if (Boolean.parseBoolean(result.get("STATUS").toString())) {
                            propietario_DNI_Data = FXCollections.observableArrayList();
                            List<Propietario> list = propietarioService.ListarPropietarioxDni(txtDNI.getText().trim());
                            tablaGestionPropietarios.setItems(FXCollections.observableArrayList(new ArrayList<>()));
                            if (list != null) {
                                for (Propietario propietario : list) {
                                    PropitarioProperty pro = new PropitarioProperty(propietario.getIdPropietario(), propietario.getNombres(), propietario.getApellidoPaterno(), propietario.getApellidoMaterno(), propietario.getCorreo(), propietario.getDni(), propietario.getDpto(), propietario.getEstado(), propietario.getIdGenero());
                                    propietario_DNI_Data.add(pro);
                                }
                                tablaGestionPropietarios.setItems(propietario_DNI_Data);
                            }else{
                                tablaGestionPropietarios.setItems(FXCollections.observableArrayList(propietarioData));
                                mostrarMensaje("El DNI no se encuentra registrado", "DNI", IconosAwesome.INFO);
                            }
                        } else {
                            System.out.println("DNI no valido");
                            tablaGestionPropietarios.setItems(FXCollections.observableArrayList(propietarioData));
                            mostrarMensaje(result.get("MSG").toString(), result.get("CAMPO").toString(), IconosAwesome.ERROR);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        tablaGestionPropietarios.setItems(FXCollections.observableArrayList(propietarioData));
                        mostrarMensaje("Ocurrio error ..", "Mensaje Error", IconosAwesome.ERROR);

                    }
                }
            }
        });
    }

    private void configureCollumns() {
        //tablaGestionEventos.setSelectionModel(null);
        idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getIdPropietario()));
        dniColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDNI()));
        nombresColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombres().trim()+" "+cellData.getValue().getApellidoPaterno().trim()+ " "+ cellData.getValue().getApellidoMaterno().trim()));
        dptoColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDpto()));
        correoColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCorreo()));
        estadoColumn.setCellValueFactory(cellData -> {
            String res = "BAJA";
            if (cellData.getValue().getEstado().equals("1")) {
                res = "ACTIVO";
            }
            return new SimpleObjectProperty<>(res);
        });

        //add cell of button edit 
        Callback<TableColumn<PropitarioProperty, String>, TableCell<PropitarioProperty, String>> cellFoctory = (TableColumn<PropitarioProperty, String> param) -> {
            // make cell containing buttons
            final TableCell<PropitarioProperty, String> cell;
            cell = new TableCell<PropitarioProperty, String>() {

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
                            if (tablaGestionPropietarios.getSelectionModel() != null) {
                                PropitarioProperty propietario = tablaGestionPropietarios.getSelectionModel().getSelectedItem();
                                try {
                                    propietarioService.desactivarPropietario(Integer.valueOf(propietario.getIdPropietario()));
                                    mostrarMensaje("Propietario Desactivado", "Informacion", IconosAwesome.INFO);
                                    llenarDatosEnTabla();
                                } catch (Exception e) {
                                }
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.err.println("EDITTT");
                            if (tablaGestionPropietarios.getSelectionModel() != null) {
                                PropitarioProperty propietario = tablaGestionPropietarios.getSelectionModel().getSelectedItem();
                                fxRegistroPropietarios.getController().open(contentMain, propietario.getPropietario(), gestion);
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
            List<Propietario> list = propietarioService.ListarPropietariosActivos();
            tablaGestionPropietarios.setItems(FXCollections.observableArrayList(new ArrayList<>()));
            if (list != null) {
                for (Propietario propietario : list) {
                    PropitarioProperty pro = new PropitarioProperty(propietario.getIdPropietario(), propietario.getNombres(), propietario.getApellidoPaterno(),propietario.getApellidoMaterno(), propietario.getCorreo(),propietario.getDni(), propietario.getDpto(), propietario.getEstado(), propietario.getIdGenero());
                     propietarioData.add(pro);
                }
                tablaGestionPropietarios.setItems(propietarioData);
            }
        } catch (Exception e) {
            System.out.println("Error llenarDatosEnTabla =" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String mensaje, String titulo, IconosAwesome icono) {
        fxDialogo.getController().AsignarValores(titulo, mensaje, icono);
        fxDialogo.getController().open(contentMain);
    }
}
