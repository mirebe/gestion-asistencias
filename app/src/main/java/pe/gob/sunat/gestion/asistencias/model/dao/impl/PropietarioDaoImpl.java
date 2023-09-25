
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pe.gob.sunat.gestion.asistencias.model.dao.PropietarioDao;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;

/**
 *
 * @author mireb
 */
public class PropietarioDaoImpl implements PropietarioDao{
 private final Connection cn;
   
    public PropietarioDaoImpl(Connection cn) {
        this.cn = cn;
    }
    
    @Override
    public Propietario buscarPropietarioxDni(String dni) throws Exception{
        
        
        Propietario pro = null;

        PreparedStatement ps = cn.prepareStatement("select * from propietario where dni=? ");
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            pro = new Propietario();
            pro.setIdPropietario(rs.getInt("idPropietario"));
            pro.setNombres(rs.getString("nombres"));
            pro.setApellidoPaterno(rs.getString("apellidoPaterno"));
            pro.setApellidoMaterno(rs.getString("apellidoMaterno"));
            pro.setDni(rs.getString("dni"));
            pro.setCorreo(rs.getString("correo"));
            pro.setDpto(rs.getInt("dpto"));
            pro.setEstado(rs.getInt("estado"));
            pro.setEstado(rs.getInt("idGenero"));
            pro.setIdCondominio(rs.getInt("idCondominio"));
        }
        rs.close();
        ps.close();

        return pro;
    }

    @Override
    public List <Propietario> buscarPropietarios() throws Exception {
        ObservableList<Propietario> listPropietarios = FXCollections.observableArrayList();
         System.out.println("as");
        
        try (PreparedStatement ps = cn.prepareStatement("select dni,concat(nombres,' ',apellidoPaterno,' ',apellidoMaterno) as nombrecompleto,dpto,correo,estado from propietario")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Propietario pro = new Propietario();
                pro.setDni(rs.getString("dni"));
                pro.setNombres(rs.getString("nombrecompleto"));
                pro.setDpto(rs.getInt("dpto"));
                pro.setCorreo(rs.getString("correo"));
                pro.setEstado(rs.getInt("estado"));
                listPropietarios.add(pro);
            }
            rs.close();
        }
              
       return listPropietarios;
    }
}