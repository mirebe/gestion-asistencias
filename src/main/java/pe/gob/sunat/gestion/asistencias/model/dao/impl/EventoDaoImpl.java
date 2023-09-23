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
public class EventoDaoImpl implements EventoDao {

    private final Connection cn;

    private String Q_LISTAR_EVENTOS_ACTIVOS = "select * from evento where estado = 1";
    private String Q_REGISTRAR_EVENTOS_ACTIVOS = "Insert into Evento(descripcion, fechaEvento, horaEvento, estado, anioEvento) VALUES(?,?,?,?,?)";
    private String Q_LISTAR_TODOS_EVENTOS = "select * from evento";
    private String Q_BUSCAR_EVENTOS_IDEVENTO = "select * from evento where idEvento=?";
    private String Q_ACTUALIZAR_EVENTOS_IDEVENTO = "update evento set descripcion=?,fechaEvento=?,horaEvento=?,anioEvento=? where idEvento=?";
    private String Q_DESACTIVAR_EVENTOS_IDEVENTO = "update evento set estado=0 where idEvento=?";
    private String Q_BUSCAR_EVENTOS_FECHA = "select * from evento where fechaEvento=?";
    private String Q_BUSCAR_EVENTOS_ANIO = "select * from evento where anioEvento=?";

    public EventoDaoImpl(Connection cn) {
        this.cn = cn;
    }

    @Override
    public List<Evento> listarEventosActivos() throws Exception {
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
    public void guardarEvento(Evento evento) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(Q_REGISTRAR_EVENTOS_ACTIVOS);
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
    public List<Evento> listarEvento() throws Exception {
        List<Evento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_LISTAR_TODOS_EVENTOS);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setIdEvento(rs.getInt("idEvento"));
            evento.setDescripcion(rs.getString("descripcion"));
            evento.setFechaEvento(rs.getDate("fechaEvento").toLocalDate());
            evento.setHoraEvento(rs.getTimestamp("horaEvento").toLocalDateTime());
            evento.setEstado(rs.getInt("estado"));
            evento.setAnioEvento(rs.getInt("anioEvento"));
            lista.add(evento);
        }
        rs.close();
        ps.close();
        return lista;
    }

    @Override
    public Evento buscarEventoPorId(Long idEvento) throws Exception {
        List<Evento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_BUSCAR_EVENTOS_IDEVENTO);
        ps.setLong(1, idEvento);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setIdEvento(rs.getInt("idEvento"));
            evento.setDescripcion(rs.getString("descripcion"));
            evento.setFechaEvento(rs.getDate("fechaEvento").toLocalDate());
            evento.setHoraEvento(rs.getTimestamp("horaEvento").toLocalDateTime());
            evento.setEstado(rs.getInt("estado"));
            evento.setAnioEvento(rs.getInt("anioEvento"));
            lista.add(evento);
        }
        rs.close();
        ps.close();
        return lista.get(0);
    }

    @Override
    public void actualizarEvento(Evento evento) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(Q_ACTUALIZAR_EVENTOS_IDEVENTO);
            pstmt.setString(1, evento.getDescripcion());
            pstmt.setDate(2, java.sql.Date.valueOf(evento.getFechaEvento()));
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(evento.getHoraEvento()));
            pstmt.setInt(4, java.sql.Date.valueOf(evento.getFechaEvento()).getYear());
            pstmt.setInt(5, evento.getIdEvento());
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
    public void desactivarEvento(Long idEvento) throws Exception{
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(Q_DESACTIVAR_EVENTOS_IDEVENTO);
            pstmt.setLong(1, idEvento);
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
    public List<Evento> buscarEventoPorFecha(LocalDate fechaEvento) throws Exception{
        List<Evento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_BUSCAR_EVENTOS_FECHA);
        ps.setDate(1, java.sql.Date.valueOf(fechaEvento));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setIdEvento(rs.getInt("idEvento"));
            evento.setDescripcion(rs.getString("descripcion"));
            evento.setFechaEvento(rs.getDate("fechaEvento").toLocalDate());
            evento.setHoraEvento(rs.getTimestamp("horaEvento").toLocalDateTime());
            evento.setEstado(rs.getInt("estado"));
            evento.setAnioEvento(rs.getInt("anioEvento"));
            lista.add(evento);
        }
        rs.close();
        ps.close();
        return lista;
    }

    @Override
    public List<Evento> buscarEventoPorAnio(int anioEvento) throws Exception{
        List<Evento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_BUSCAR_EVENTOS_ANIO);
        ps.setInt(1, anioEvento);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setIdEvento(rs.getInt("idEvento"));
            evento.setDescripcion(rs.getString("descripcion"));
            evento.setFechaEvento(rs.getDate("fechaEvento").toLocalDate());
            evento.setHoraEvento(rs.getTimestamp("horaEvento").toLocalDateTime());
            evento.setEstado(rs.getInt("estado"));
            evento.setAnioEvento(rs.getInt("anioEvento"));
            lista.add(evento);
        }
        rs.close();
        ps.close();
        return lista;
    }
}
