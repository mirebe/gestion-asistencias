/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service;

import java.time.LocalDate;
import java.util.List;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;

/**
 *
 * @author mireb
 */
public interface EventoService {
    public List<Evento> listarEventosActivos() throws Exception;
    
    void guardarEvento(Evento evento) throws Exception;

    List<Evento> listarEvento() throws Exception;

    Evento buscarEventoPorId(Long idEvento) throws Exception;

    void actualizarEvento(Evento evento) throws Exception;

    void desactivarEvento(Long idEvento) throws Exception;

    List<Evento> buscarEventoPorFecha(LocalDate fechaEvento) throws Exception;

    List<Evento> buscarEventoPorAnio(int anioEvento) throws Exception;
    
}
