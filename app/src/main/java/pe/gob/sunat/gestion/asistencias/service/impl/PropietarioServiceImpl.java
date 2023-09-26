/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import pe.gob.sunat.gestion.asistencias.model.dao.PropietarioDao;
import pe.gob.sunat.gestion.asistencias.model.dao.impl.PropietarioDaoImpl;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;
import pe.gob.sunat.gestion.asistencias.model.util.Conexion;
import pe.gob.sunat.gestion.asistencias.service.PropietarioService;

/**
 *
 * @author mireb
 */
public class PropietarioServiceImpl implements PropietarioService {
    private PropietarioDao propietarioDao;

    public PropietarioServiceImpl() {
        propietarioDao = new PropietarioDaoImpl(Conexion.getConnect());
    }
    
    
     
    @Override
    public List<Propietario> ListarPropietarioxDni (String dni) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Propietario> buscarPropietarios() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<Propietario> ListarPropietariosActivos() throws Exception {
        return propietarioDao.ListarPropietariosActivos();
    }
    
    
    @Override
    public Map<String, Object> validarDatosPropietario(Propietario propietario, Integer[] reglas) throws Exception {

        Map<String, Object> validacion = new HashMap<>();
        validacion.put("STATUS", true);
        for (Integer regla : reglas) {
            switch (regla) {
                case 1://Validaciones para Dni.
                    if (propietario.getDni().trim().length() == 0) {
                        validacion.put("STATUS", false);
                        validacion.put("COD", "P001");
                        validacion.put("CAMPO", "DNI");
                        validacion.put("MSG", "Debe ingresar el número de DNI");
                        return validacion;
                    }
          
                    if (!Pattern.matches("\\d{8}", propietario.getDni().trim())) {
                        validacion.put("STATUS", false);
                        validacion.put("COD", "P002");
                        validacion.put("CAMPO", "DNI");
                        validacion.put("MSG", "El número de DNI debe contener 8 dígitos numéricos.");
                        return validacion;
                    }
                default:
                    break;
            }
        }
        return validacion;
    }

    @Override
    public void desactivarPropietario(Integer idPropietario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
