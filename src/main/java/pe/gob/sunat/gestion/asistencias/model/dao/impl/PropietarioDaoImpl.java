/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pe.gob.sunat.gestion.asistencias.model.dao.PropietarioDao;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;

/**
 *
 * @author mireb
 */
public class PropietarioDaoImpl implements PropietarioDao{
    private final Connection cn;

    private String Q_BUSCAR_X_DNI = "select * from propietario where dni = ? ";
    
    public PropietarioDaoImpl(Connection cn) {
        this.cn = cn;
    }
    
    @Override
    public Propietario buscarPropietarioxDni(String dni) throws Exception{
        Propietario pro = null;

        PreparedStatement ps = cn.prepareStatement(Q_BUSCAR_X_DNI);
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            pro = new Propietario();
            pro.setIdPropietario(rs.getInt("idPropietario"));
            pro.setNombres(rs.getString("nombres"));
            pro.setApellidoPaterno(rs.getString("apellidoPaterno"));
            pro.setApellidoMaterno(rs.getString("apellidoMaterno"));
        }
        rs.close();
        ps.close();

        return pro;
    }
    
}
