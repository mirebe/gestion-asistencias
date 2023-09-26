/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao;

import java.util.List;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;

/**
 *
 * @author mireb
 */
public interface PropietarioDao {
    public Propietario buscarPropietarioxDni(String dni) throws Exception;
    public List<Propietario> listarPropietario() throws Exception;
    public List <Propietario> ListarPropietarioxDni(String dni) throws Exception;
    public List <Propietario> ListarPropietariosActivos() throws Exception;
}
