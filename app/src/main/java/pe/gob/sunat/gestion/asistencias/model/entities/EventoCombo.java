/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mireb
 */
public class EventoCombo {

    private Long idEvento;
    private String descripcion;

    public EventoCombo() {

    }
    public EventoCombo(Long idEvento,String descripcion) {
        this.idEvento = idEvento;
        this.descripcion = descripcion;
    }

    /**
     * @return the idEvento
     */
    public Long getIdEvento() {
        return idEvento;
    }

    /**
     * @param idEvento the idEvento to set
     */
    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
        @Override
    public String toString() {
        return this.getDescripcion();
    }
    
    
}
