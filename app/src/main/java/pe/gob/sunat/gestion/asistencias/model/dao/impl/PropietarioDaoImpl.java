
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
    private final String Q_LISTAR_TODOS_PROPIETARIOS = "select * from propietario where estado=1";
    private final String Q_REGISTRAR_ACTIVOS = "Insert into propietario(idCondominio, nombres, apellidoPaterno, apellidoMaterno, idGenero,correo,estado,dni,dpto) VALUES(?,?,?,?,?,?,?,?,?)";
    private final String Q_UPDATE = "UPDATE propietario SET\n" +
" nombres = ?, apellidoPaterno = ?, apellidoMaterno = ?, idGenero = ?,\n" +
" correo = ?, estado = ?, dni = ?, dpto = ? WHERE idPropietario = ?;";
    private final String Q_DESACTIVAR = "update propietario set estado=0 where idPropietario=?";
     private final String Q_LISTAR_X_NOMBRES = "select * from propietario where concat(apellidoMaterno , apellidoMaterno , nombres)  like ?";
    
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
                    rs.getString("correo"), rs.getString("dni"),rs.getInt("dpto"),rs.getInt("idGenero")));
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
    public List<Propietario> listarPropietarioxNombre(String nombre) throws Exception {
        List<Propietario> lista = new ArrayList<>();
        Propietario pro = null;
        PreparedStatement ps = cn.prepareStatement(Q_LISTAR_X_NOMBRES);
        ps.setString(1, "%"+nombre+"%");
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
            lista.add(pro);
        }
        rs.close();
        ps.close();
        return lista;
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
    
    
    @Override
    public void guardar(Propietario propietario) throws Exception {
        //idCondominio, nombres, apellidoPaterno, apellidoMaterno, idGenero,correo,estado,dni,dpto
        PreparedStatement pstmt = cn.prepareStatement(Q_REGISTRAR_ACTIVOS);
        pstmt.setInt(1, 1);
        pstmt.setString(2, propietario.getNombres());
        pstmt.setString(3, propietario.getApellidoPaterno());
        pstmt.setString(4, propietario.getApellidoMaterno());
        pstmt.setInt(5, propietario.getIdGenero());
        pstmt.setString(6, propietario.getCorreo());
        pstmt.setString(7, "1");
        pstmt.setString(8, propietario.getDni());
        pstmt.setInt(9, propietario.getDpto());
       
        pstmt.executeUpdate();
        pstmt.close();
    }
    
    @Override
    public void actualizar(Propietario pro) throws Exception {
        
        PreparedStatement pstmt  = cn.prepareStatement(Q_UPDATE);
        pstmt.setString(1, pro.getNombres());
        pstmt.setString(2, pro.getApellidoPaterno());
        pstmt.setString(3, pro.getApellidoMaterno());
        pstmt.setInt(4, pro.getIdGenero());
        pstmt.setString(5, pro.getCorreo());
        pstmt.setInt(6, 1);
        pstmt.setString(7, pro.getDni());
        pstmt.setInt(8, pro.getDpto());
        pstmt.setLong(9, pro.getIdPropietario());
        pstmt.executeUpdate();
        
        pstmt.close();
    }
    
    @Override
    public void desactivar(Long idPropietario) throws Exception {
        PreparedStatement  pstmt = cn.prepareStatement(Q_DESACTIVAR);
        pstmt.setLong(1, idPropietario);
        pstmt.executeUpdate();
        pstmt.close();         
    }
}