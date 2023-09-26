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
    public List <Propietario> buscarPropietarios() throws Exception;
    public List<Propietario> listarPropietario() throws Exception;
    List<Propietario> listarPropietarioxNombre(String nombre) throws Exception;
    
    void desactivar(Long idPropietario) throws Exception;
    void actualizar(Propietario propietario) throws Exception;
    void guardar(Propietario propietario) throws Exception;
}
