/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao;

import java.util.List;
import pe.gob.sunat.gestion.asistencias.model.entities.Asistencia;

/**
 *
 * @author mireb
 */
public interface AsistenciaDao {
    public void guardar(Asistencia asistencia) throws Exception;
    public boolean existeAsistencia(Long idPropietario,Long idEvento) throws Exception ;
    public List<Asistencia> listarAsistencias(Long idEvento,Long idPropietario) throws Exception ;
}
