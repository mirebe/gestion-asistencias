/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service;

import java.util.Map;
import pe.gob.sunat.gestion.asistencias.model.entities.Asistencia;

/**
 *
 * @author mireb
 */
public interface AsistenciaService {
    boolean grabarAsistencia(Asistencia asistencia)  throws Exception;
    Map<String,Object> buscarPropietario(String dniProp,Integer[] reglas) throws Exception;
}
