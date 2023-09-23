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
public class Evento {
    private ObjectProperty<Long> idEvento;
    private StringProperty descripcion;
    private ObjectProperty<LocalDate> fechaEvento;
    private ObjectProperty<LocalDateTime> horaEvento;
    private ObjectProperty<Integer> anioEvento;
    private ObjectProperty<Integer> estado;

    public Evento() {
        this.idEvento = new SimpleObjectProperty<>(0L);
        this.descripcion = new SimpleStringProperty("");
        this.fechaEvento = new SimpleObjectProperty<>(LocalDate.now());
        this.horaEvento = new SimpleObjectProperty<>(LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 0, 0));
        this.anioEvento = new SimpleObjectProperty<>(0);
        this.estado = new SimpleObjectProperty<>(0);
        
    }
    
    public Evento(Long idEvento, String descripcion, LocalDate fechaEvento, LocalDateTime horaEvento, Integer anioEvento, Integer estado) {
        this.idEvento = new SimpleObjectProperty<>(idEvento);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.fechaEvento = new SimpleObjectProperty<>(fechaEvento);
        this.horaEvento = new SimpleObjectProperty<>(horaEvento);
        this.anioEvento = new SimpleObjectProperty<>(anioEvento);
        this.estado = new SimpleObjectProperty<>(estado);
    }
    
    public Evento(Long idEvento, String descripcion) {
        this.idEvento = new SimpleObjectProperty<>(idEvento);
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    /**
     * @return the idEvento
     */
    public ObjectProperty<Long> getIdEvento1() {
        return idEvento;
    }

    /**
     * @param idEvento the idEvento to set
     */
    public void setIdEvento1(ObjectProperty<Long> idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * @return the descripcion
     */
    public StringProperty getDescripcion1() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion1(StringProperty descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaEvento
     */
    public ObjectProperty<LocalDate> getFechaEvento1() {
        return fechaEvento;
    }

    /**
     * @param fechaEvento the fechaEvento to set
     */
    public void setFechaEvento1(ObjectProperty<LocalDate> fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    /**
     * @return the horaEvento
     */
    public ObjectProperty<LocalDateTime> getHoraEvento1() {
        return horaEvento;
    }

    /**
     * @param horaEvento the horaEvento to set
     */
    public void setHoraEvento1(ObjectProperty<LocalDateTime> horaEvento) {
        this.horaEvento = horaEvento;
    }

    /**
     * @return the anioEvento
     */
    public ObjectProperty<Integer> getAnioEvento1() {
        return anioEvento;
    }

    /**
     * @param anioEvento the anioEvento to set
     */
    public void setAnioEvento1(ObjectProperty<Integer> anioEvento) {
        this.anioEvento = anioEvento;
    }

    /**
     * @return the estado
     */
    public ObjectProperty<Integer> getEstado1() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado1(ObjectProperty<Integer> estado) {
        this.estado = estado;
    }


    public Long getIdEvento() {
        return idEvento.get();
    }
    public void setIdEvento(Long idEvento) {
        this.idEvento.set(idEvento);
    }
    public String getDescripcion() {
        return descripcion.get();
    }
    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    public LocalDate getFechaEvento() {
        return fechaEvento.get();
    }
    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento.set(fechaEvento);
    }
    public LocalDateTime getHoraEvento() {
        return horaEvento.get();
    }
    public void setHoraEvento(LocalDateTime horaEvento) {
        this.horaEvento.set(horaEvento);
    }
    public Integer getAnioEvento() {
        return anioEvento.get();
    }

    public void setAnioEvento(Integer anioEvento) {
        this.anioEvento.set(anioEvento);
    }
    public Integer getEstado() {
        return estado.get();
    }
    public void setEstado1(Integer estado) {
        this.estado.set(estado);
    }
    
}
