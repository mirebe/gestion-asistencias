/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;

/**
 *
 * @author mireb
 */
public class EventoProperty {
    private final StringProperty idEvento;
    private final StringProperty descripcion;
    private final StringProperty fechaEvento;
    private final StringProperty horaEvento;
    private  StringProperty estado = new SimpleStringProperty("1");
    
    private Evento evento;
    
    public EventoProperty(Long idEvento, String descripcion, LocalDate fechaEvento, LocalDateTime horaEvento, Integer estado) {
        this.idEvento = new SimpleStringProperty(idEvento.toString());
        this.descripcion = new SimpleStringProperty(descripcion);
        this.fechaEvento = new SimpleStringProperty(fechaEvento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.horaEvento = new SimpleStringProperty(horaEvento.format(DateTimeFormatter.ofPattern("hh:mm a")));
        this.estado = new SimpleStringProperty(estado.toString());
        evento = new Evento(idEvento, descripcion, fechaEvento, horaEvento,0, estado);
    }

    public Evento getEvento() {
        return evento;
    }

    public String getIdEvento() {
        return idEvento.getValue();
    }

    public String getDescripcion() {
        return descripcion.getValue();
    }

    public String getFechaEvento() {
        return fechaEvento.getValue();
    }

    public String getHoraEvento() {
        return horaEvento.getValue();
    }

    public String getEstado() {
        return estado.getValue();
    }
    
    
}
