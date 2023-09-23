/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pe.gob.sunat.gestion.asistencias.model.dao.PropietarioDao;
import pe.gob.sunat.gestion.asistencias.model.dao.impl.PropietarioDaoImpl;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;
import pe.gob.sunat.gestion.asistencias.model.util.Conexion;
import pe.gob.sunat.gestion.asistencias.service.PropietarioService;
import pe.gob.sunat.gestion.asistencias.service.impl.PropietarioServiceImpl;
import pe.gob.sunat.gestion.asistencias.util.FXMLArchivo;
import pe.gob.sunat.gestion.asistencias.util.IconosAwesome;

/**
 *
 * @author mireb
 */
public class GestionPropietariosController implements Initializable {

    private final PropietarioDao propietarioDao;

    private final PropietarioService propietarioService;

    private boolean Registrar;

    FXMLArchivo<DialogoMensajeController> fxDialogo;

    @FXML
    private AnchorPane contentGestionPropietario;

    @FXML
    private TableView<Propietario> propietarioTable;

    @FXML
    private TableColumn<Propietario, String> dniColumn;

    @FXML
    private TableColumn<Propietario, String> nombresColumn;

    @FXML
    private TableColumn<Propietario, String> dptoColumn;

    @FXML
    private TableColumn<Propietario, String> correoColumn;

    @FXML
    private TableColumn<Propietario, String> estadoColumn;

    private List<Propietario> propietariosList = new ArrayList<>();

    @FXML
    TextField txtDNI;

    public GestionPropietariosController() {
        System.out.println("GestionPropietariosController.<constructor>()");
        propietarioService = new PropietarioServiceImpl();
        propietarioDao = new PropietarioDaoImpl(Conexion.getConnect());
    }

    //private ObservableList<Propietario> propietarioList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
            nombresColumn.setCellValueFactory(new PropertyValueFactory<>("nombres"));
            dptoColumn.setCellValueFactory(new PropertyValueFactory<>("dpto"));
            correoColumn.setCellValueFactory(new PropertyValueFactory<>("correo"));
            estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
            // TODO
            cargartabla();
        } catch (Exception ex) {
            Logger.getLogger(GestionPropietariosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            fxDialogo = new FXMLArchivo<>("VistaDialogoMensaje");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al inicializar fxDialogo: " + ex.getMessage()); // Línea de depuración
        }

    }

    public void cargartabla() throws Exception {
        propietariosList = propietarioDao.buscarPropietarios();
        propietarioTable.setItems(FXCollections.observableArrayList(propietariosList));
    }

    private void mostrarMensaje(String mensaje, String titulo, IconosAwesome icono) {
        fxDialogo.getController().AsignarValores(titulo, mensaje, icono);
        fxDialogo.getController().open(contentGestionPropietario);
    }

    @FXML
    private void onAction_buscarRegistrar() {
        Registrar = true;
        onAction_buscarPorDNI();
    }

    @FXML
    private void onAction_buscarPorDNI() {

        Propietario pro = new Propietario();
        pro.setDni(txtDNI.getText().trim());
        Integer[] valores;
        valores = new Integer[]{1};
        Map<String, Object> result;
        try {
            result = propietarioService.validarDatosPropietario(pro, valores);
            if (Boolean.parseBoolean(result.get("STATUS").toString())) {
                if (Registrar) {
                    Propietario pro1 = propietarioDao.buscarPropietarioxDni(pro.getDni());
                    if (pro1 == null) {
                        System.out.println("no se encontro");
                        System.out.println(pro.getDni());
                    } else {
                         System.out.println("lo encontro");
                        System.out.println(pro1.getCorreo());
                    }
                } else {
                    List<Propietario> resultados = propietariosList.stream()
                            .filter(propietario -> propietario.getDni().equalsIgnoreCase(pro.getDni()))
                            .collect(Collectors.toList());
                    if (resultados.isEmpty()) {
                        // Si no se encontraron resultados, restaura la lista original de propietarios
                        propietarioTable.setItems(FXCollections.observableArrayList(propietariosList));
                        mostrarMensaje("No se encontro registro para el DNI", "DNI", IconosAwesome.INFO);
                    } else {
                        // Si se encontraron resultados, actualiza la TableView con ellos
                        propietarioTable.setItems(FXCollections.observableArrayList(resultados));
                    }
                }
                // Utiliza Java Streams para filtrar la lista por DNI

            } else {
                System.out.println("DNI no valido");
                propietarioTable.setItems(FXCollections.observableArrayList(propietariosList));
                mostrarMensaje(result.get("MSG").toString(), result.get("CAMPO").toString(), IconosAwesome.ERROR);
                Registrar = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            propietarioTable.setItems(FXCollections.observableArrayList(propietariosList));
            mostrarMensaje("Ocurrio error ..", "Mensaje Error", IconosAwesome.ERROR);
            Registrar = false;

        }
    }

}