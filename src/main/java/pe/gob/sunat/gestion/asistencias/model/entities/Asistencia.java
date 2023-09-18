/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.entities;

import java.time.LocalDateTime;

/**
 *
 * @author mireb
 */
public class Asistencia {
    Integer idAsistencia;
    Integer idPropietario;
    Integer idEvento;
    LocalDateTime fechaHoraAsistencia;
    Double montoMulta;
    String estado;

    public Asistencia(Integer idAsistencia, Integer idPropietario, Integer idEvento, LocalDateTime fechaHoraAsistencia, Double montoMulta, String estado) {
        this.idAsistencia = idAsistencia;
        this.idPropietario = idPropietario;
        this.idEvento = idEvento;
        this.fechaHoraAsistencia = fechaHoraAsistencia;
        this.montoMulta = montoMulta;
        this.estado = estado;
    }

    public Asistencia() {
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Integer getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public LocalDateTime getFechaHoraAsistencia() {
        return fechaHoraAsistencia;
    }

    public void setFechaHoraAsistencia(LocalDateTime fechaHoraAsistencia) {
        this.fechaHoraAsistencia = fechaHoraAsistencia;
    }

    public Double getMontoMulta() {
        return montoMulta;
    }

    public void setMontoMulta(Double montoMulta) {
        this.montoMulta = montoMulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    
}
