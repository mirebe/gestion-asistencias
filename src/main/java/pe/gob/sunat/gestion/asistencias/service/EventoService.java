/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service;

import java.util.List;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;

/**
 *
 * @author mireb
 */
public interface EventoService {
    public List<Evento> listarEventosActivos() throws Exception;
}
