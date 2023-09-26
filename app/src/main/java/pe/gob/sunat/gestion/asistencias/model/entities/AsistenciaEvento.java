/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.entities;


/**
 *
 * @author mireb
 */
public class AsistenciaEvento {
    private Long idEvento;
    private Long capacidad;

    public AsistenciaEvento(Long idEvento, Long capacidad) {
        this.idEvento = idEvento;
        this.capacidad = capacidad;

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
     * @return the capacidad
     */
    public Long getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(Long capacidad) {
        this.capacidad = capacidad;
    }

   
    
}
