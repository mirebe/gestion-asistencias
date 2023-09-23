/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import pe.gob.sunat.gestion.asistencias.model.dao.AsistenciaDao;
import pe.gob.sunat.gestion.asistencias.model.dao.PropietarioDao;
import pe.gob.sunat.gestion.asistencias.model.dao.impl.AsistenciaDaoImpl;
import pe.gob.sunat.gestion.asistencias.model.dao.impl.PropietarioDaoImpl;
import pe.gob.sunat.gestion.asistencias.model.entities.Asistencia;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;
import pe.gob.sunat.gestion.asistencias.model.util.Conexion;
import pe.gob.sunat.gestion.asistencias.service.AsistenciaService;
import pe.gob.sunat.gestion.asistencias.service.client.RestClientHora;

/**
 *
 * @author mireb
 */
public class AsistenciaServiceImpl implements AsistenciaService{
    private AsistenciaDao asistenciaDao;
    private PropietarioDao propietarioDao;

    public AsistenciaServiceImpl() {
        asistenciaDao = new AsistenciaDaoImpl(Conexion.getConnect());
        propietarioDao = new PropietarioDaoImpl(Conexion.getConnect());
    }

    @Override
    public boolean grabarAsistencia(Asistencia asistencia) throws Exception{
        RestClientHora rest = new RestClientHora();
        LocalDateTime hora = rest.obtenerHoraWoldTimeApi();
        asistencia.setFechaHoraAsistencia(hora);
        
        System.out.println(hora);
        if(!asistenciaDao.existeAsistencia(asistencia.getIdPropietario(), asistencia.getIdEvento())){
            asistenciaDao.guardar(asistencia);
            return true;
        }
        return false;
    }
    
    public Map<String,Object> buscarPropietario(String dniProp,Integer[] reglas) throws Exception{
        Map<String,Object> response = new HashMap<>();
	response.put("STATUS", true);
	for (Integer regla : reglas) {
            switch (regla) {
                case 1://Validaciones para el usuario.
                    if (dniProp.trim().length() == 0) {
			response.put("STATUS", false);
			response.put("MSG", "Debe ingresar el numero DNI");
			return response;
                    }
                    break;
		case 2://Validaciones para el password.
                    if (dniProp.length() != 8 || !isNumeric(dniProp)) {
			response.put("STATUS", false);
			response.put("MSG", "Debe ingresar 8 digitos númericos");
			return response;
                    }
                    break;
                case 3://valida base datos
                    Propietario pro = propietarioDao.buscarPropietarioxDni(dniProp);
                    if (pro == null) {
			response.put("STATUS", false);
			response.put("MSG", "Propietario no se encuentra registrado.");
			return response;
                    }else{
                        response.put("propietario", pro);
                    }
                    break;    
		default:
                    break;				
            }
        }
	return response;
    }
    
    private boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
}
