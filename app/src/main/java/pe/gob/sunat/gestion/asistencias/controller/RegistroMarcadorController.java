package pe.gob.sunat.gestion.asistencias.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pe.gob.sunat.gestion.asistencias.model.entities.Asistencia;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;
import pe.gob.sunat.gestion.asistencias.service.AsistenciaService;
import pe.gob.sunat.gestion.asistencias.service.EventoService;
import pe.gob.sunat.gestion.asistencias.service.impl.AsistenciaServiceImpl;
import pe.gob.sunat.gestion.asistencias.service.impl.EventoServiceImpl;
import pe.gob.sunat.gestion.asistencias.util.FXMLArchivo;
import pe.gob.sunat.gestion.asistencias.util.IconosAwesome;
import pe.gob.sunat.gestion.asistencias.util.ProcesosAsincronos;

/**
 *
 * @author mireb
 */
public class RegistroMarcadorController implements Initializable{
    private Stage stageVentanaRegistroMarcacion;
    private Stage stageVentanaLogin;
    private LoginController loginController;
    
    private final AsistenciaService asistenciaService;
    private final EventoService eventosService;
    FXMLArchivo<DialogoMensajeController> fxDialogo;
    ObservableList<Evento> itemsCombo = FXCollections.observableArrayList(); 
    
    @FXML
    TextField txtDni;
    @FXML
    AnchorPane contentMain;
    @FXML
    Label lblNombres;
    @FXML
    ComboBox<Evento> cboEvento;
    @FXML
    Button btnMarcar;
    @FXML
    Label lblReloj;
    
    Propietario propietario = null;
    
    public RegistroMarcadorController() {
        this.asistenciaService = new AsistenciaServiceImpl();
        this.eventosService= new EventoServiceImpl();
    }
    
    @FXML
    private void onClick_buscarPropietario(){
        try {
            Map<String,Object> response  = asistenciaService.buscarPropietario(txtDni.getText(),new Integer[]{1,2,3});
            if(Boolean.parseBoolean(response.get("STATUS").toString())){
                propietario = (Propietario) response.get("propietario");
                lblNombres.setText(propietario.getNombresCompleto());
                btnMarcar.setDisable(false);
            }else{
                lblNombres.setText("");
                propietario = null;
                btnMarcar.setDisable(true);
                mostrarMensaje(response.get("MSG").toString(), "Mensaje Error", IconosAwesome.ERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarMensaje("Ocurrio error ..", "Mensaje Error", IconosAwesome.ERROR);
        }
    }
    
    @FXML
    private void onClick_marcarIngreso(){
        Evento evento = cboEvento.getSelectionModel().getSelectedItem();
        if(propietario == null){
             mostrarMensaje("Propietario no encontrado", "Mensaje Error", IconosAwesome.ERROR);
        }
        if(evento!=null){
            //System.err.println("ID:"+evento.getIdEvento()+" DES:"+evento.getDescripcion());
            try {
                Asistencia as = new Asistencia();
                as.setIdPropietario(propietario.getIdPropietario().longValue());
                as.setIdEvento(evento.getIdEvento().longValue());
                boolean estado = asistenciaService.grabarAsistencia(as);
                if(estado){
                    mostrarMensaje("Se registro su asistencia correctamente!!!!", "Mensaje Aviso", IconosAwesome.CORRECTO);
                }else{
                    mostrarMensaje("Ya se encuentra registrado en el evento!!!.", "Mensaje Error", IconosAwesome.ERROR);
                }
               
                lblNombres.setText("");
                propietario = null;
                btnMarcar.setDisable(true);
                cboEvento.getSelectionModel().clearSelection();
                txtDni.clear();
            } catch (Exception ex) {
                mostrarMensaje("Ocurrio un error al registrar", "Mensaje Error", IconosAwesome.ERROR);
                Logger.getLogger(RegistroMarcadorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            mostrarMensaje("Seleccione el evento a registrarse.", "Mensaje Error", IconosAwesome.ERROR);
            System.out.println("seleccione...........");
        }
        
    }
    
    @FXML
    private void onClicked_lblSalir(MouseEvent event) {
    	stageVentanaRegistroMarcacion.close();
        loginController.showVentanaLogin();
    }
    
    void init(Stage stageVentanaLogin, Stage stageVentanaRegistroMarcacion,LoginController loginController) {
        this.loginController = loginController;
        this.stageVentanaLogin = stageVentanaLogin;
        this.stageVentanaRegistroMarcacion = stageVentanaRegistroMarcacion;
        List<Evento> lista;
        try {
            lista = eventosService.listarEventosActivos();
            itemsCombo.addAll(lista);
            cboEvento.setItems(itemsCombo);
        } catch (Exception ex) {
            Logger.getLogger(RegistroMarcadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateFechaSistema();
    }
    
    private void updateFechaSistema(){
        ProcesosAsincronos pro = new ProcesosAsincronos();
        pro.Ejecutar(() -> {
             DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
            while (true) {
                 try {
                       Thread.sleep(1000);
                } catch (InterruptedException e) {}
                 Platform.runLater(() -> {
                     lblReloj.setText(formateador.format(LocalDateTime.now()));
                 });
            }
        }); 
    }
    
    private void mostrarMensaje(String mensaje, String titulo, IconosAwesome icono) {
        fxDialogo.getController().AsignarValores(titulo, mensaje, icono);
        fxDialogo.getController().open(contentMain);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fxDialogo = new FXMLArchivo<>("VistaDialogoMensaje");
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
