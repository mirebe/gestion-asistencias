/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pe.gob.sunat.gestion.asistencias.enums.EstadoEnum;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;
import pe.gob.sunat.gestion.asistencias.service.EventoService;
import pe.gob.sunat.gestion.asistencias.service.impl.EventoServiceImpl;

/**
 *
 * @author mireb
 */
public class GestionEventosController implements Initializable {

    @FXML
    private TableView<Evento> tablaGestionEventos;

    @FXML
    private TableColumn<Evento, Long> idColumn;

    @FXML
    private TableColumn<Evento, String> nombreColumn;

    @FXML
    private TableColumn<Evento, LocalDate> fechaColumn;

    @FXML
    private TableColumn<Evento, String> horaColumn;

    @FXML
    private TableColumn<Evento, String> estadoColumn;

    @FXML
    private TextField txtNombreEvento;

    @FXML
    private DatePicker datePikerFechaEvento;

    @FXML
    private ComboBox comboHora;

    @FXML
    private ComboBox comboMinuto;

    @FXML
    private ComboBox comboSiglas;

    @FXML
    private Button btnRegistrarEvento;

    @FXML
    private Button btnLimpiarEvento;

    private ObservableList<Evento> eventoData = FXCollections.observableArrayList();

    private Evento eventoActual = new Evento(0L, "", LocalDate.now(), LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 0, 0), 0, 0);

    private final EventoService eventoService;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    public GestionEventosController() {
        System.out.println("GestionEventosController.<constructor>()");
        eventoService = new EventoServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCombos();
        enlazarTabla();
        llenarDatosEnTabla();
        seleccionarElementoDeTabla();
    }

    private void cargarCombos() {
        comboHora.setItems((ObservableList) listarHora());
        comboMinuto.setItems((ObservableList) listarMinuto());
        comboSiglas.setItems((ObservableList) listarSiglas());
    }

    private void seleccionarEvento(Evento eventoNuevo) {
        if (eventoNuevo != null) {
            eventoActual.setIdEvento(eventoNuevo.getIdEvento());
            eventoActual.setDescripcion(eventoNuevo.getDescripcion());
            eventoActual.setFechaEvento(eventoNuevo.getFechaEvento());
            eventoActual.setHoraEvento(eventoNuevo.getHoraEvento());
            eventoActual.setEstado1(eventoNuevo.getEstado());
        } else {
            eventoActual = new Evento(0l, "", LocalDate.now(), LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 0, 0), 0, 0);
        }
    }

    private void enlazarTabla() {
        tablaGestionEventos.setItems(eventoData);
        txtNombreEvento.textProperty().bindBidirectional(eventoActual.getDescripcion1());
        datePikerFechaEvento.valueProperty().bindBidirectional(eventoActual.getFechaEvento1());
//        String horaMinutoData = dtf.format(eventoActual.getHoraEvento());
//        comboHora.valueProperty().bindBidirectional(new SimpleObjectProperty(obtenerHora(horaMinutoData)));
//        comboMinuto.valueProperty().bindBidirectional(new SimpleObjectProperty(obtenerMinuto(horaMinutoData)));
//        comboSiglas.valueProperty().bindBidirectional(new SimpleObjectProperty(obtenerMarcador(horaMinutoData)));

        idColumn.setCellValueFactory(rowData -> rowData.getValue().getIdEvento1());
        nombreColumn.setCellValueFactory(rowData -> rowData.getValue().getDescripcion1());
        fechaColumn.setCellValueFactory(rowData -> rowData.getValue().getFechaEvento1());
        horaColumn.setCellValueFactory(rowData -> {
            SimpleStringProperty respuesta = new SimpleStringProperty();
            String horaMinuto = dtf.format(rowData.getValue().getHoraEvento());
            respuesta.set(convertirMarcador(horaMinuto));
            return respuesta;
        });
        estadoColumn.setCellValueFactory(rowData -> new SimpleStringProperty(EstadoEnum.getStringValueFromInt(rowData.getValue().getEstado())));
    }

    @FXML
    private void limpiarTabla() {
        eventoActual = new Evento(0L, "", LocalDate.now(), LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 0, 0), 0, 0);
        cargarCombos();
    }

    private void llenarDatosEnTabla() {
        try {
            List<Evento> list = eventoService.listarEvento();
            eventoData.addAll(list);
        } catch (Exception e) {
            System.out.println("Error llenarDatosEnTabla =" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void guardarEvento(ActionEvent event) {
        if (eventoActual.getIdEvento() == 0) {

            if (eventoActual.getDescripcion().isEmpty() || eventoActual.getDescripcion().equals("")) {
                mostrarAlertas("Warning", "Ingrese Descripcion del Evento", Alert.AlertType.WARNING);
                return;
            }
            if (eventoActual.getFechaEvento() == null || eventoActual.getFechaEvento().equals("")) {
                mostrarAlertas("Warning", "Ingrese Fecha del Evento", Alert.AlertType.WARNING);
                return;
            }
            if (comboHora.getValue() == null || comboHora.getValue().equals("")) {
                mostrarAlertas("Warning", "Ingrese Hora", Alert.AlertType.WARNING);
                return;
            }
            if (comboMinuto.getValue() == null || comboMinuto.getValue().equals("")) {
                mostrarAlertas("Warning", "Ingrese Minuto", Alert.AlertType.WARNING);
                return;
            }
            if (comboSiglas.getValue() == null || comboSiglas.getValue().equals("")) {
                mostrarAlertas("Warning", "Ingrese Macador", Alert.AlertType.WARNING);
                return;
            }
            eventoActual.setHoraEvento(LocalDateTime.of(eventoActual.getFechaEvento().getYear(), eventoActual.getFechaEvento().getMonth(), eventoActual.getFechaEvento().getDayOfMonth(), Integer.parseInt(convertir24H(comboHora.getValue().toString(),comboSiglas.getValue().toString())), Integer.parseInt(comboMinuto.getValue().toString())));
            eventoActual.setAnioEvento(eventoActual.getFechaEvento().getYear());
            eventoActual.setEstado1(EstadoEnum.ACTIVO.getValor());
            try {
                eventoService.guardarEvento(eventoActual);
                mostrarAlertas("Informacion", "Se guardo exitosamente", Alert.AlertType.INFORMATION);
            } catch (Exception ex) {
                ex.printStackTrace();
                mostrarAlertas("ERROR", "Excepcion="+ex.getMessage(), Alert.AlertType.ERROR);
            }
            llenarDatosEnTabla();
        }
    }

    private void seleccionarElementoDeTabla() {
        tablaGestionEventos.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Evento> ov, Evento eventoAntiguo, Evento eventoNuevo) -> {
            seleccionarEvento(eventoNuevo);
            StringBinding btnGuardarText = new StringBinding() {
                @Override
                protected String computeValue() {
                    if (eventoActual.getIdEvento() == 0) {
                        return "Guardar";
                    }
                    return "Actualizar";
                }
            };
            btnRegistrarEvento.textProperty().bind(btnGuardarText);
        });
    }

    private void mostrarAlertas(String header, String content, Alert.AlertType type) {
        Alert dialogo = new Alert(type);
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.show();
    }

    private ObservableList<String> listarHora() {
        ObservableList<String> listaHora = FXCollections.observableArrayList();
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

    private ObservableList<String> listarSiglas() {
        ObservableList<String> listaSigla = FXCollections.observableArrayList();
        listaSigla.add("am");
        listaSigla.add("pm");
        return listaSigla;
    }

    private String convertirMarcador(String horaMinuto) {
        String horaConMarcador = "";
        String marcador = "am";
        String[] hhmm = horaMinuto.split(":");
        String hh = hhmm[0];
        String mm = hhmm[1];
        int horaInt = Integer.parseInt(hh);
        if (horaInt >= 0 && horaInt < 12) {
            horaConMarcador = armarHora12H(hh) + ":" + mm + " " + marcador;
            return horaConMarcador;
        } else {
            marcador = "pm";
            horaConMarcador = armarHora12H(hh) + ":" + mm + " " + marcador;
            return horaConMarcador;
        }
    }

    private String armarHora12H(String hora) {
        String hora24H = "";
        int horaEntero = Integer.parseInt(hora);
        if (horaEntero > 12 && horaEntero <= 23) {
            switch (horaEntero) {
                case 13:
                    hora24H = "01";
                    break;
                case 14:
                    hora24H = "02";
                    break;
                case 15:
                    hora24H = "03";
                    break;
                case 16:
                    hora24H = "04";
                    break;
                case 17:
                    hora24H = "05";
                    break;
                case 18:
                    hora24H = "06";
                    break;
                case 19:
                    hora24H = "07";
                    break;
                case 20:
                    hora24H = "08";
                    break;
                case 21:
                    hora24H = "09";
                    break;
                case 22:
                    hora24H = "10";
                    break;
                case 23:
                    hora24H = "11";
                    break;
            }
        } else {
            hora24H = hora;
        }
        return hora24H;
    }

    private String convertir24H(String hora, String marcador) {
        String hora12H = "";
        int horaEntero = Integer.parseInt(hora);
        if (marcador.equals("pm")) {
            switch (horaEntero) {
                case 1:
                    hora12H = "13";
                    break;
                case 2:
                    hora12H = "14";
                    break;
                case 3:
                    hora12H = "15";
                    break;
                case 4:
                    hora12H = "16";
                    break;
                case 5:
                    hora12H = "17";
                    break;
                case 6:
                    hora12H = "18";
                    break;
                case 7:
                    hora12H = "19";
                    break;
                case 8:
                    hora12H = "20";
                    break;
                case 9:
                    hora12H = "21";
                    break;
                case 10:
                    hora12H = "22";
                    break;
                case 11:
                    hora12H = "23";
                    break;
            }
        } else {
            hora12H = hora;
        }
        return hora12H;
    }

    private String obtenerHora(String horaMinuto) {
        String[] hhmm = horaMinuto.split(":");
        String hh = hhmm[0];
        return hh;
    }

    private String obtenerMinuto(String horaMinuto) {
        String[] hhmm = horaMinuto.split(":");
        String mm = hhmm[1];
        return mm;
    }

    private String obtenerMarcador(String horaMinuto) {
        String marcador = "am";
        String[] hhmm = horaMinuto.split(":");
        String hh = hhmm[0];
        int horaInt = Integer.parseInt(hh);
        if (horaInt >= 0 && horaInt < 12) {
            return marcador;
        } else {
            marcador = "pm";
            return marcador;
        }
    }
}
