/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service.impl;

import java.time.LocalDate;
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
public class EventoServiceImpl implements EventoService {

    private EventoDao eventoDao;

    public EventoServiceImpl() {
        eventoDao = new EventoDaoImpl(Conexion.getConnect());
    }

    @Override
    public List<Evento> listarEventosActivos() throws Exception {
        List<Evento> eventos = eventoDao.listarEventosActivos();
        return eventos;
    }

    @Override
    public void guardarEvento(Evento evento) throws Exception {
        eventoDao.guardarEvento(evento);
    }

    @Override
    public List<Evento> listarEvento() throws Exception {
        List<Evento> eventos = eventoDao.listarEvento();
        return eventos;
    }

    @Override
    public Evento buscarEventoPorId(Long idEvento) throws Exception {
        List<Evento> eventos = eventoDao.listarEvento();
        return eventos.get(0);
    }

    @Override
    public void actualizarEvento(Evento evento) throws Exception {
        eventoDao.actualizarEvento(evento);
    }

    @Override
    public void desactivarEvento(Long idEvento) throws Exception {
        eventoDao.desactivarEvento(idEvento);
    }

    @Override
    public List<Evento> buscarEventoPorFecha(LocalDate fechaEvento) throws Exception {
        List<Evento> eventos = eventoDao.buscarEventoPorFecha(fechaEvento);
        return eventos;
    }

    @Override
    public List<Evento> buscarEventoPorAnio(int anioEvento) throws Exception {
        List<Evento> eventos = eventoDao.buscarEventoPorAnio(anioEvento);
        return eventos;
    }
    
     @Override
    public List<Evento> listarEventoxNombre(String nombre) throws Exception{
        List<Evento> eventos = eventoDao.listarEventoxNombre(nombre);
        return eventos;
    }
}
