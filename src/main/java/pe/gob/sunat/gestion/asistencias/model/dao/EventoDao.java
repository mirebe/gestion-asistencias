/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao;

import java.util.List;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;

/**
 *
 * @author mireb
 */
public interface EventoDao {
    public List<Evento> listarEventosActivos() throws Exception;
    
    void guardarEvento(Evento evento);

    List<Evento> listarEvento();

    Evento buscarEventoPorId(Long idEvento);

    void actualizarEvento(Evento evento);

    void eliminarEvento(Long idEvento);
}
