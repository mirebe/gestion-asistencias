/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service;

import java.util.Map;
import pe.gob.sunat.gestion.asistencias.model.entities.Usuario;

/**
 *
 * @author mireb
 */
public interface LoginService{
    public Usuario obtenerDatosUsuario(String codUsuario,String pwd) throws Exception ;
    public Map<String,Object> validarDatosLogin(Usuario usuario,Integer[] reglas) throws Exception;
}
