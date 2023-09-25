/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service.impl;


import java.util.HashMap;
import java.util.Map;
import pe.gob.sunat.gestion.asistencias.model.entities.Usuario;
import pe.gob.sunat.gestion.asistencias.model.dao.UsuarioDao;
import pe.gob.sunat.gestion.asistencias.model.util.Conexion;
import pe.gob.sunat.gestion.asistencias.model.dao.impl.UsuarioDaoImpl;
import pe.gob.sunat.gestion.asistencias.service.LoginService;

/**
 *
 * @author mireb
 */
public class LoginServiceImpl implements LoginService{
    
    private UsuarioDao usuarioDao;

    public LoginServiceImpl() {
        usuarioDao = new UsuarioDaoImpl(Conexion.getConnect());
    }
    
    @Override
    public Usuario obtenerDatosUsuario(String codUsuario,String pwd) throws Exception {
      return usuarioDao.findById(codUsuario,pwd);
    }
    
    
    @Override
    public Map<String,Object> validarDatosLogin(Usuario usuario,Integer[] reglas) throws Exception{
        Map<String,Object> validacion = new HashMap<>();
	validacion.put("STATUS", true);
	for (Integer regla : reglas) {
            switch (regla) {
                case 1://Validaciones para el usuario.
                    if (usuario.getUsuario().trim().length() == 0) {
			validacion.put("STATUS", false);
			validacion.put("COD", "L001");
			validacion.put("CAMPO", "Usuario");
			validacion.put("MSG", "Debe ingresar el Usuario");
			return validacion;
                    }
                    break;
		case 2://Validaciones para el password.
                    if (usuario.getClave().trim().length() == 0) {
			validacion.put("STATUS", false);
			validacion.put("COD", "L002");
			validacion.put("CAMPO", "Contrase\u00f1a");
			validacion.put("MSG", "Debe de ingresar la contrase\u00f1a");
			return validacion;
                    }
                    break;
                case 3://valida base datos
                    Usuario us = usuarioDao.findById(usuario.getUsuario(),usuario.getClave());
                    if (us == null) {
			validacion.put("STATUS", false);
			validacion.put("COD", "L002");
			validacion.put("CAMPO", "Mensaje");
			validacion.put("MSG", "El usuario o la contrase\u00f1a es incorrecta");
			return validacion;
                    }else{
                        validacion.put("usuario", us);
                    }
                    break;    
		default:
                    break;				
            }
        }
	return validacion;
    }
    
}
