/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import pe.gob.sunat.gestion.asistencias.model.entities.Asistencia;
import pe.gob.sunat.gestion.asistencias.model.dao.AsistenciaDao;

/**
 *
 * @author mireb
 */
public class AsistenciaDaoImpl implements AsistenciaDao{
    private final Connection cn;
    
    String Q_INSERT = "INSERT INTO asistencia (idPropietario, idEvento, fechaHoraAsistencia, montoMulta,estado) VALUES (?, ?, ?, 0,1)";
    
    public AsistenciaDaoImpl(Connection cn) {
        this.cn = cn;
    }
    
    @Override
    public void guardar(Asistencia asistencia)throws Exception {
        PreparedStatement statement = cn.prepareStatement(Q_INSERT);
        statement.setInt(1, asistencia.getIdPropietario());
        statement.setInt(2,asistencia.getIdEvento());
        statement.setTimestamp(3,Timestamp.valueOf(asistencia.getFechaHoraAsistencia()) );
 
        statement.executeUpdate();
    }
    
}
