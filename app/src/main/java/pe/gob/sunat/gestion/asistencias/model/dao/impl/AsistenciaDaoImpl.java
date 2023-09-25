/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
    public AsistenciaDaoImpl(Connection cn) {
        this.cn = cn;
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
