/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    @Override
    public void guardarEvento(Evento evento) {
        PreparedStatement pstmt = null;
        try {

            String sql = "Insert into Evento(descripcion, fechaEvento, horaEvento, estado, anio) VALUES(?,?,?,?,?)";
            pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, evento.getDescripcion());
            pstmt.setDate(2, java.sql.Date.valueOf(evento.getFechaEvento()));
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(evento.getHoraEvento()));
            pstmt.setInt(4, evento.getEstado());
            pstmt.setInt(5, evento.getAnioEvento());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }
    }

    @Override
    public List<Evento> listarEvento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Evento buscarEventoPorId(Long idEvento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarEvento(Evento evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarEvento(Long idEvento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
