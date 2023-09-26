
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    private final String Q_LISTAR_TODOS_PROPIETARIOS = "select * from propietario";
    private final String Q_LISTAR_PROPIETARIOS_ACTIVOS = "select * from propietario where estado = 1";
    private final String Q_LISTAR_PROPIETARIOS_X_DNI = "select * from propietario where dni = ?";

    
    
    
    public PropietarioDaoImpl(Connection cn) {
        this.cn = cn;
    }
    
    @Override
    public List<Propietario> listarPropietario() throws Exception {
        List<Propietario> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_LISTAR_TODOS_PROPIETARIOS);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Propietario(rs.getLong("idPropietario"), rs.getString("nombres"),
                    rs.getString("apellidoPaterno"), rs.getString("apellidoMaterno"),
                    rs.getString("correo"), rs.getString("dni"),rs.getInt("dpto")));
        }
        rs.close();
        ps.close();
        return lista;
    }
    
    @Override
    public Propietario buscarPropietarioxDni(String dni) throws Exception{
        
        
        Propietario pro = null;

        PreparedStatement ps = cn.prepareStatement("select * from propietario where dni=? ");
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            pro = new Propietario();
            pro.setIdPropietario(rs.getLong("idPropietario"));
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
    public List<Propietario> ListarPropietariosActivos() throws Exception {
        ObservableList<Propietario> listPropietarios = FXCollections.observableArrayList();

        try (PreparedStatement ps = cn.prepareStatement(Q_LISTAR_PROPIETARIOS_ACTIVOS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Propietario pro = new Propietario();
                pro.setIdPropietario(rs.getLong("idPropietario"));
                pro.setNombres(rs.getString("nombres"));
                pro.setApellidoPaterno(rs.getString("apellidoPaterno"));
                pro.setApellidoMaterno(rs.getString("apellidoMaterno"));
                pro.setDni(rs.getString("dni"));
                pro.setCorreo(rs.getString("correo"));
                pro.setDpto(rs.getInt("dpto"));
                pro.setEstado(rs.getInt("estado"));
                pro.setIdGenero(rs.getInt("idGenero"));
                pro.setIdCondominio(rs.getInt("idCondominio"));
                listPropietarios.add(pro);
            }
            rs.close();
        }

        return listPropietarios;
    }

    @Override
    public List<Propietario> ListarPropietarioxDni(String dni) throws Exception {
        ObservableList<Propietario> listPropietarios = FXCollections.observableArrayList();

        try (PreparedStatement ps = cn.prepareStatement(Q_LISTAR_PROPIETARIOS_X_DNI)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Propietario pro = new Propietario();
                pro.setIdPropietario(rs.getLong("idPropietario"));
                pro.setNombres(rs.getString("nombres"));
                pro.setApellidoPaterno(rs.getString("apellidoPaterno"));
                pro.setApellidoMaterno(rs.getString("apellidoMaterno"));
                pro.setDni(rs.getString("dni"));
                pro.setCorreo(rs.getString("correo"));
                pro.setDpto(rs.getInt("dpto"));
                pro.setEstado(rs.getInt("estado"));
                pro.setIdGenero(rs.getInt("idGenero"));
                pro.setIdCondominio(rs.getInt("idCondominio"));
                listPropietarios.add(pro);
            }
            rs.close();
        }

        return listPropietarios;
    }
}