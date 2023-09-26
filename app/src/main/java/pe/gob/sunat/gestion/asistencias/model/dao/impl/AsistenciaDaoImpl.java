/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import pe.gob.sunat.gestion.asistencias.model.entities.Asistencia;
import pe.gob.sunat.gestion.asistencias.model.dao.AsistenciaDao;

/**
 *
 * @author mireb
 */
public class AsistenciaDaoImpl implements AsistenciaDao{
    private final Connection cn;
    
    String Q_INSERT = "INSERT INTO asistencia (idPropietario, idEvento, fechaHoraAsistencia, montoMulta,estado) VALUES (?, ?, ?, 0,1)";
    String Q_EXISTE_ASISTENCIA = "select 1 from asistencia where idPropietario=? and idEvento=? limit 1;";
    String Q_BUSCAR_LISTA_ASISTENCIAS = "select a.idAsistencia,a.fechaHoraAsistencia, a.idPropietario, a.idEvento, \n" +
                                        "b.descripcion evento, b.horaEvento,\n" +
                                        "c.apellidoPaterno, c.apellidoMaterno, c.nombres , c.dni, c.dpto, c.correo\n" +
                                        "from asistencia a \n" +
                                        "left join evento b on a.idEvento = b.idEvento\n" +
                                        "left join propietario c on a.idPropietario = c.idPropietario ";
    
    public AsistenciaDaoImpl(Connection cn) {
        this.cn = cn;
    }
    
    @Override
    public List<Asistencia> listarAsistencias(Long idEvento,Long idPropietario) throws Exception {
        String query = Q_BUSCAR_LISTA_ASISTENCIAS;
        List<Asistencia> lista = new ArrayList<>();
        
        if(idEvento != null || idPropietario!=null){
            query += " WHERE ";
            if(idEvento != null){
                 query += " a.idEvento = "+idEvento+ " AND ";
            }
            if(idPropietario != null){
                query += " a.idPropietario = "+idPropietario + "";
            }else{
                query = query.replace("AND","");
            }  
        }
        PreparedStatement ps = cn.prepareStatement(query);
       
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Asistencia asis = new Asistencia();
            asis.setIdAsistencia(rs.getLong("idAsistencia"));
            asis.setIdPropietario(rs.getLong("idPropietario"));
            asis.setIdEvento(rs.getLong("idEvento"));
            asis.setFechaHoraAsistencia(rs.getTimestamp("fechaHoraAsistencia").toLocalDateTime());
            asis.getEvento().setDescripcion(rs.getString("evento"));
            asis.getEvento().setHoraEvento(rs.getTimestamp("horaEvento").toLocalDateTime());
            asis.getPropietario().setApellidoMaterno(rs.getString("apellidoMaterno"));
            asis.getPropietario().setApellidoPaterno(rs.getString("apellidoPaterno"));
            asis.getPropietario().setNombres(rs.getString("nombres"));
            asis.getPropietario().setDni(rs.getString("dni"));
            asis.getPropietario().setDpto(rs.getInt("dpto"));
            asis.getPropietario().setCorreo(rs.getString("correo"));
            lista.add(asis);
        }
        rs.close();
        ps.close();
        return lista;
    }
    
    @Override
    public void guardar(Asistencia asistencia)throws Exception {
        PreparedStatement statement = cn.prepareStatement(Q_INSERT);
        statement.setLong(1, asistencia.getIdPropietario());
        statement.setLong(2,asistencia.getIdEvento());
        statement.setTimestamp(3,Timestamp.valueOf(asistencia.getFechaHoraAsistencia()) );
        statement.executeUpdate();
        statement.close();
    }
    
    @Override
    public boolean existeAsistencia(Long idPropietario,Long idEvento) throws Exception {
        boolean existe = false;
        PreparedStatement ps = cn.prepareStatement(Q_EXISTE_ASISTENCIA);
        ps.setLong(1, idPropietario);
        ps.setLong(2, idEvento);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            existe = true;
            break;
        }
        rs.close();
        ps.close();
        return existe;
    }
    
}
