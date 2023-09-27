package pe.gob.sunat.gestion.asistencias.model.dao;

import java.time.LocalDate;
import java.util.List;
import pe.gob.sunat.gestion.asistencias.model.entities.AsistenciaEvento;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;
import pe.gob.sunat.gestion.asistencias.model.entities.EventoCombo;
import pe.gob.sunat.gestion.asistencias.model.entities.EventoDashboard;

/**
 *
 * @author mireb
 */
public interface EventoDao {

    public List<Evento> listarEventosActivos() throws Exception;

    void guardarEvento(Evento evento) throws Exception;
    
    void guardarEvento(Evento evento, int cantidadPropietarios) throws Exception;

    List<Evento> listarEvento() throws Exception;
    
    List<Evento> listarEventoxNombre(String nombre) throws Exception;

    Evento buscarEventoPorId(Long idEvento) throws Exception;

    void actualizarEvento(Evento evento) throws Exception;

    void desactivarEvento(Long idEvento) throws Exception;

    List<Evento> buscarEventoPorFecha(LocalDate fechaEvento) throws Exception;

    List<Evento> buscarEventoPorAnio(int anioEvento) throws Exception;

    public List<EventoCombo> listarEventosActivos2() throws Exception;

     public List<EventoDashboard> listarEventoDashboard(int tipoC, String txtbusqueda, Long idEvento) throws Exception;
     
     public     List<AsistenciaEvento> listarAsistents(Long idEvento) throws Exception ;    
    
}
