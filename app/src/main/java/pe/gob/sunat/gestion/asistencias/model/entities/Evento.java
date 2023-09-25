/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author mireb
 */
public class Evento {
    private Long idEvento;
    private String descripcion = "";
    private LocalDate fechaEvento;
    private LocalDateTime horaEvento;
    private Integer anioEvento = 0;
    private Integer estado = 1;

    public Evento(Long idEvento, String descripcion, LocalDate fechaEvento, LocalDateTime horaEvento, Integer anioEvento, Integer estado) {
        this.idEvento = idEvento;
        this.descripcion = descripcion;
        this.fechaEvento = fechaEvento;
        this.horaEvento = horaEvento;
        this.anioEvento = anioEvento;
        this.estado = estado;
    }

    public Evento(Long idEvento, String descripcion, LocalDate fechaEvento, LocalDateTime horaEvento) {
        this.idEvento = idEvento;
        this.descripcion = descripcion;
        this.fechaEvento = fechaEvento;
        this.horaEvento = horaEvento;
    }
    
    

    public Evento(Long idEvento, String descripcion) {
        this.idEvento = idEvento;
        this.descripcion = descripcion;    
    }

    public Evento() {
    }
    
    

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public LocalDateTime getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(LocalDateTime horaEvento) {
        this.horaEvento = horaEvento;
    }

    public Integer getAnioEvento() {
        return anioEvento;
    }

    public void setAnioEvento(Integer anioEvento) {
        this.anioEvento = anioEvento;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

     @Override
    public String toString() {
        return this.descripcion;
    }
}
