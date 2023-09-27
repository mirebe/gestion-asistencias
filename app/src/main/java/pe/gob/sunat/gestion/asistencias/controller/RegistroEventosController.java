/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;
import pe.gob.sunat.gestion.asistencias.service.EventoService;
import pe.gob.sunat.gestion.asistencias.service.PropietarioService;
import pe.gob.sunat.gestion.asistencias.service.impl.EventoServiceImpl;
import pe.gob.sunat.gestion.asistencias.service.impl.PropietarioServiceImpl;
import pe.gob.sunat.gestion.asistencias.util.FormularioUtil;

/**
 *
 * @author mireb
 */
public class RegistroEventosController implements Initializable{
    private AnchorPane parent;
    private GestionEventosController gestionEventosController;
    private Evento evento;
    private EventoService eventoService;
    private PropietarioService propietarioService;

    public RegistroEventosController() {
        eventoService = new EventoServiceImpl();
        propietarioService = new PropietarioServiceImpl();
    }

    @FXML
    private TextField txtNombreEvento;

    @FXML
    private DatePicker datePikerFechaEvento;

    @FXML
    private ComboBox<String> comboHora,comboMinuto;

    @FXML
    private RadioButton rbAM, rbPM ;
    
    private String tipoOperacion = "INS" ; //INS,UPD
    
    @FXML
    private AnchorPane contenMain;
    
    @FXML
    private void onClick_btnSalir(){
        if(parent != null){
            parent.getChildren().remove(contenMain);
        }
    }
    
    @FXML
    private void onActionCancelar(){
        if(parent != null){
            parent.getChildren().remove(contenMain);
        }
    }
    
    @FXML
    private void onActionAceptar(){
        if(txtNombreEvento.getText().length()==0){
            mostrarAlertas("Warning", "Ingrese Descripcion del Evento", Alert.AlertType.WARNING);
            return;
        }
        if(datePikerFechaEvento.getEditor().getText().length()==0){
             mostrarAlertas("Warning", "Ingrese Fecha del Evento", Alert.AlertType.WARNING);
            return;
        }
        if(comboHora.getSelectionModel().getSelectedItem().length()==0){
             mostrarAlertas("Warning", "Ingrese Hora", Alert.AlertType.WARNING);
            return;
        }
        if(comboMinuto.getEditor().getText().length()==0){
             mostrarAlertas("Warning", "Ingrese Minuto", Alert.AlertType.WARNING);
            return;
        }
        if(!rbPM.isSelected() && !rbAM.isSelected() ){
            mostrarAlertas("Warning", "Ingrese Macador", Alert.AlertType.WARNING);
            return;
        }
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        
        evento.setDescripcion(txtNombreEvento.getText());
        evento.setFechaEvento(LocalDate.parse(datePikerFechaEvento.getEditor().getText(),DateTimeFormatter.ofPattern("d/MM/yyyy")));
        //parsea hora
        String hora = comboHora.getSelectionModel().getSelectedItem()+":"+comboMinuto.getEditor().getText();
        if(rbPM.isSelected()){
            hora = hora +" "+"p. m.";
        }else{
            hora = hora +" "+"a. m.";
        }
        System.err.println("LA HORA ES  "+hora);
        LocalDateTime local = LocalDateTime.of(evento.getFechaEvento(), LocalTime.from(timeFormatter.parse(hora)));
        evento.setHoraEvento(local);
         ///////////////////////
        evento.setAnioEvento(evento.getFechaEvento().getYear());
        try {
            int cantidadPropietarios=propietarioService.contarPropietario();
            System.out.println("cantidadPropietarios=="+cantidadPropietarios);
            evento.setEstado(1);
            if(tipoOperacion.equals("INS") ){
                eventoService.guardarEvento(evento,cantidadPropietarios);
                mostrarAlertas("Informacion", "Se agrego un nuevo evento exitosamente", Alert.AlertType.INFORMATION);
            }else{
                eventoService.actualizarEvento(evento);
                 mostrarAlertas("Informacion", "Se actualizo evento exitosamente", Alert.AlertType.INFORMATION);
            }
            
            if(parent != null){
                parent.getChildren().remove(contenMain);
            }
            gestionEventosController.llenarDatosEnTabla();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlertas("ERROR", "Excepcion="+ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
       
    private void cargarCombos() {
        comboHora.setItems((ObservableList) listarHora());
        comboMinuto.setItems((ObservableList) listarMinuto());
        ToggleGroup toggleGroup = new ToggleGroup();
        rbAM.setToggleGroup(toggleGroup);
        rbPM.setToggleGroup(toggleGroup);
        FormularioUtil.txtMaxLenFormat(comboMinuto.getEditor(), "[012345]?[\\d]?$");
    }
   
    public void open(AnchorPane contentMain,Evento evento,GestionEventosController gestionEventosController) {
        this.parent = contentMain;
        this.gestionEventosController = gestionEventosController;
        AnchorPane.setBottomAnchor(contenMain, 0.0);
	AnchorPane.setTopAnchor(contenMain, 0.0);
	AnchorPane.setLeftAnchor(contenMain, 0.0);
	AnchorPane.setRightAnchor(contenMain, 0.0);
        
        System.out.println("::::: Open mensaje ");
        if(contentMain != null){
             contentMain.getChildren().add(contenMain);
            asignarValoresIniciales( evento);
        }
        this.evento = evento;
        if(evento.getIdEvento()!=null && evento.getIdEvento().intValue()>0){
            tipoOperacion = "UPD";
        }
        
        System.out.println("::::: Open mensaje ");
    }
    
    private void asignarValoresIniciales(Evento evento){
        txtNombreEvento.setText(evento.getDescripcion());
        if(evento.getFechaEvento()!=null){
            datePikerFechaEvento.getEditor().setText(evento.getFechaEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }else{
            datePikerFechaEvento.getEditor().clear();
        }
        if(evento.getHoraEvento()!= null){
            String[] horaCadena = evento.getHoraEvento().format(DateTimeFormatter.ofPattern("hh:mm:a")).split(":");
            System.out.println("HORA "+horaCadena[0]+"-"+horaCadena[1]);
            comboHora.getSelectionModel().select(horaCadena[0]);
            comboMinuto.getEditor().setText(""+horaCadena[1]);
            if(horaCadena[2].contains("p")){
                rbPM.setSelected(true);
            }else{
                rbAM.setSelected(true);
            }
        }else{
            comboHora.getEditor().clear();
            comboHora.getSelectionModel().clearSelection();
            comboMinuto.getEditor().clear();
            comboMinuto.getSelectionModel().clearSelection();
            rbAM.setSelected(false);
            rbPM.setSelected(false);
        }
        
    }
    
    private ObservableList<String> listarHora() {
        ObservableList<String> listaHora = FXCollections.observableArrayList();
        listaHora.add("00");
        listaHora.add("01");
        listaHora.add("02");
        listaHora.add("03");
        listaHora.add("04");
        listaHora.add("05");
        listaHora.add("06");
        listaHora.add("07");
        listaHora.add("08");
        listaHora.add("09");
        listaHora.add("10");
        listaHora.add("11");
        listaHora.add("12");
        return listaHora;
    }

    private ObservableList<String> listarMinuto() {
        ObservableList<String> listaMinuto = FXCollections.observableArrayList();
        listaMinuto.add("00");
        listaMinuto.add("05");
        listaMinuto.add("10");
        listaMinuto.add("15");
        listaMinuto.add("20");
        listaMinuto.add("25");
        listaMinuto.add("30");
        listaMinuto.add("35");
        listaMinuto.add("40");
        listaMinuto.add("45");
        listaMinuto.add("50");
        listaMinuto.add("55");
        return listaMinuto;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarCombos();
    }
    
    private void mostrarAlertas(String header, String content, Alert.AlertType type) {
        Alert dialogo = new Alert(type);
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.show();
    }
}
