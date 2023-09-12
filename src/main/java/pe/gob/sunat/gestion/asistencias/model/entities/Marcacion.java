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
public class Marcacion {
    Integer idMarca;
    Integer idTrab;
    LocalDateTime fechaHora;
    String indIncidencia;

    public Marcacion() {
    }

    public Marcacion(Integer idMarca, Integer idTrab, LocalDateTime fechaHora, String indIncidencia) {
        this.idMarca = idMarca;
        this.idTrab = idTrab;
        this.fechaHora = fechaHora;
        this.indIncidencia = indIncidencia;
    }

    
    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdTrab() {
        return idTrab;
    }

    public void setIdTrab(Integer idTrab) {
        this.idTrab = idTrab;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIndIncidencia() {
        return indIncidencia;
    }

    public void setIndIncidencia(String indIncidencia) {
        this.indIncidencia = indIncidencia;
    }

    
}
