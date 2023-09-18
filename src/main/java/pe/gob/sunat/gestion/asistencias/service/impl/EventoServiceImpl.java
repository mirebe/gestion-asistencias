/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service.impl;

import java.util.ArrayList;
import java.util.List;
import pe.gob.sunat.gestion.asistencias.model.dao.EventoDao;
import pe.gob.sunat.gestion.asistencias.model.dao.impl.EventoDaoImpl;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;
import pe.gob.sunat.gestion.asistencias.model.util.Conexion;
import pe.gob.sunat.gestion.asistencias.service.EventoService;

/**
 *
 * @author mireb
 */
public class EventoServiceImpl implements EventoService{
    private EventoDao eventoDao;

    public EventoServiceImpl() {
        eventoDao = new EventoDaoImpl(Conexion.getConnect());
    }
    
    @Override
    public List<Evento> listarEventosActivos() throws Exception{
        List<Evento> eventos = eventoDao.listarEventosActivos();
        return eventos;
    }
}
