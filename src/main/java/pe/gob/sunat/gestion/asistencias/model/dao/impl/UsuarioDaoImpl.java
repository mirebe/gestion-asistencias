/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pe.gob.sunat.gestion.asistencias.model.entities.Usuario;
import pe.gob.sunat.gestion.asistencias.model.dao.UsuarioDao;

/**
 *
 * @author mireb
 */
public class UsuarioDaoImpl implements UsuarioDao{
    
    private Connection cn;
    private static final String QUERY_FIND_BY_ID ="SELECT * FROM usuario WHERE usuario=? and contrasenia=? ";

    public UsuarioDaoImpl(Connection cn) {
        this.cn = cn;
    }
    
    @Override
    public Usuario findById(String codUsu,String pwd) throws Exception {
        Usuario usuario = null;

        PreparedStatement ps = cn.prepareStatement(QUERY_FIND_BY_ID);
        ps.setString(1, codUsu);
        ps.setString(2, pwd);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            usuario = new Usuario();
            usuario.setIdPropietario(rs.getInt("idUsuario"));
            usuario.setNombres("nombres");
            usuario.setUsuario("usuario");
        }
        rs.close();
        ps.close();

        return usuario;
    }


}
