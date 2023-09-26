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
    public Propietario buscarPropietarioxDni(String dni) throws Exception;
    public  List <Propietario> buscarPropietarios() throws Exception;
    public Map<String,Object> validarDatosPropietario(Propietario propietario,Integer[] reglas) throws Exception;
    public List<Propietario> listarPropietario() throws Exception ;
    
    List<Propietario> listarPropietarioxNombre(String nombre) throws Exception;
    void desactivar(Long idPropietario) throws Exception;
    void actualizar(Propietario propietario) throws Exception;
    void guardar(Propietario propietario) throws Exception;
}
