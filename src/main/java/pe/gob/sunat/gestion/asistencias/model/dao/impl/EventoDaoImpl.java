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
import pe.gob.sunat.gestion.asistencias.model.dao.EventoDao;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;

/**
 *
 * @author mireb
 */
public class EventoDaoImpl implements EventoDao{
    private final Connection cn;
    
    private String Q_LISTAR_EVENTOS_ACTIVOS = "select * from evento where estado = 1";
    
    public EventoDaoImpl(Connection cn) {
        this.cn = cn;
    }
    
    @Override
    public List<Evento> listarEventosActivos() throws Exception{
        List<Evento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_LISTAR_EVENTOS_ACTIVOS);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setIdEvento(rs.getInt("idEvento"));
            evento.setDescripcion(rs.getString("descripcion"));
            
            lista.add(evento);
        }
        rs.close();
        ps.close();
        
        return lista;
    }
    
}
