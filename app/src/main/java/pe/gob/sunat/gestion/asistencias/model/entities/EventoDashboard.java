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
public class EventoDashboard {

    private ObjectProperty<Long> idEvento;
    private StringProperty descripcion;
    private StringProperty nombres;
    private ObjectProperty<LocalDateTime> fechahora;
    private StringProperty nombreCondominio;
    
    
    
    
    public EventoDashboard() {
        this.idEvento = new SimpleObjectProperty<>(0L);
        this.descripcion = new SimpleStringProperty("");
        this.nombres = new SimpleStringProperty("");
        this.nombreCondominio = new SimpleStringProperty("");
        this.fechahora = new SimpleObjectProperty<>(LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 0, 0));
    }
    
 
    
    public EventoDashboard(Long idEvento,String descripcion,String nombres,LocalDateTime fechahora,String nombreCondominio) {
        this.idEvento = new SimpleObjectProperty<>(idEvento);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.nombres = new SimpleStringProperty(nombres);
        this.fechahora = new SimpleObjectProperty<>(fechahora);
        this.nombreCondominio = new SimpleStringProperty(nombreCondominio);
    }

    /**
     * @return the idEvento
     */
    public ObjectProperty<Long> getIdEvento() {
        return idEvento;
    }

    /**
     * @param idEvento the idEvento to set
     */
    public void setIdEvento(ObjectProperty<Long> idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * @return the descripcion
     */
    public StringProperty getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(StringProperty descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the nombres
     */
    public StringProperty getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(StringProperty nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the fechahora
     */
    public ObjectProperty<LocalDateTime> getFechahora() {
        return fechahora;
    }

    /**
     * @param fechahora the fechahora to set
     */
    public void setFechahora(ObjectProperty<LocalDateTime> fechahora) {
        this.fechahora = fechahora;
    }

    /**
     * @return the nombreCondominio
     */
    public StringProperty getNombreCondominio() {
        return nombreCondominio;
    }

    /**
     * @param nombreCondominio the nombreCondominio to set
     */
    public void setNombreCondominio(StringProperty nombreCondominio) {
        this.nombreCondominio = nombreCondominio;
    }
    
    
    
    
}
