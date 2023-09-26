/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service;


import java.util.List;
import java.util.Map;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;

/**
 *
 * @author mireb
 */
public interface PropietarioService {
    public  List <Propietario> buscarPropietarios() throws Exception;
    public Map<String,Object> validarDatosPropietario(Propietario propietario,Integer[] reglas) throws Exception;
    public List<Propietario> ListarPropietariosActivos() throws Exception ;
    public  List<Propietario> ListarPropietarioxDni(String dni) throws Exception;
    void desactivarPropietario(Integer idPropietario) throws Exception;   
}
