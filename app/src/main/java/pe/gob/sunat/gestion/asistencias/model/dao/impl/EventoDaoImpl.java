/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    private final String Q_LISTAR_EVENTOS_ACTIVOS = "select * from evento where estado = 1";
    private final String Q_REGISTRAR_EVENTOS_ACTIVOS = "Insert into Evento(descripcion, fechaEvento, horaEvento, estado, anioEvento) VALUES(?,?,?,?,?)";
    private final String Q_LISTAR_TODOS_EVENTOS = "select * from evento";
    private final String Q_LISTAR_X_NOMNRES_EVENTOS = "select * from evento where descripcion like ?";
    private final String Q_BUSCAR_EVENTOS_IDEVENTO = "select * from evento where idEvento=?";
    private final String Q_ACTUALIZAR_EVENTOS_IDEVENTO = "update evento set descripcion=?,fechaEvento=?,horaEvento=?,anioEvento=? where idEvento=?";
    private final String Q_DESACTIVAR_EVENTOS_IDEVENTO = "update evento set estado=0 where idEvento=?";
    private final String Q_BUSCAR_EVENTOS_FECHA = "select * from evento where fechaEvento=?";
    private final String Q_BUSCAR_EVENTOS_ANIO = "select * from evento where anioEvento=?";

    public EventoDaoImpl(Connection cn) {
        this.cn = cn;
    }

    @Override
    public List<Evento> listarEventosActivos() throws Exception {
        List<Evento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_LISTAR_EVENTOS_ACTIVOS);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Evento(rs.getLong("idEvento"), rs.getString("descripcion")));
        }
        rs.close();
        ps.close();

        return lista;
    }

    @Override
    public void guardarEvento(Evento evento) throws Exception {
        PreparedStatement pstmt = cn.prepareStatement(Q_REGISTRAR_EVENTOS_ACTIVOS);
        pstmt.setString(1, evento.getDescripcion());
        pstmt.setDate(2, java.sql.Date.valueOf(evento.getFechaEvento()));
        pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(evento.getHoraEvento()));
        pstmt.setInt(4, evento.getEstado());
        pstmt.setInt(5, evento.getAnioEvento());
        pstmt.executeUpdate();

        pstmt.close();
    }

    @Override
    public List<Evento> listarEvento() throws Exception {
        List<Evento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_LISTAR_TODOS_EVENTOS);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Evento(rs.getLong("idEvento"), rs.getString("descripcion"),
                    rs.getDate("fechaEvento").toLocalDate(), rs.getTimestamp("horaEvento").toLocalDateTime(),
                    rs.getInt("anioEvento"), rs.getInt("estado")));
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
            lista.add(new Evento(rs.getLong("idEvento"), rs.getString("descripcion"),
                    rs.getDate("fechaEvento").toLocalDate(), rs.getTimestamp("horaEvento").toLocalDateTime(),
                    rs.getInt("anioEvento"), rs.getInt("estado")));
        }
        rs.close();
        ps.close();
        return lista.get(0);
    }

    @Override
    public void actualizarEvento(Evento evento) throws Exception {
        
        PreparedStatement pstmt  = cn.prepareStatement(Q_ACTUALIZAR_EVENTOS_IDEVENTO);
        pstmt.setString(1, evento.getDescripcion());
        pstmt.setDate(2, java.sql.Date.valueOf(evento.getFechaEvento()));
        pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(evento.getHoraEvento()));
        pstmt.setInt(4, java.sql.Date.valueOf(evento.getFechaEvento()).getYear());
        pstmt.setLong(5, evento.getIdEvento());
        pstmt.executeUpdate();
        
        pstmt.close();
    }

    @Override
    public void desactivarEvento(Long idEvento) throws Exception {
        PreparedStatement  pstmt = cn.prepareStatement(Q_DESACTIVAR_EVENTOS_IDEVENTO);
        pstmt.setLong(1, idEvento);
        pstmt.executeUpdate();
        pstmt.close();         
    }

    @Override
    public List<Evento> buscarEventoPorFecha(LocalDate fechaEvento) throws Exception {
        List<Evento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_BUSCAR_EVENTOS_FECHA);
        ps.setDate(1, java.sql.Date.valueOf(fechaEvento));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Evento(rs.getLong("idEvento"), rs.getString("descripcion"),
                    rs.getDate("fechaEvento").toLocalDate(), rs.getTimestamp("horaEvento").toLocalDateTime(),
                    rs.getInt("anioEvento"), rs.getInt("estado")));
        }
        rs.close();
        ps.close();
        return lista;
    }

    @Override
    public List<Evento> buscarEventoPorAnio(int anioEvento) throws Exception {
        List<Evento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_BUSCAR_EVENTOS_ANIO);
        ps.setInt(1, anioEvento);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Evento(rs.getLong("idEvento"), rs.getString("descripcion"),
                    rs.getDate("fechaEvento").toLocalDate(), rs.getTimestamp("horaEvento").toLocalDateTime(),
                    rs.getInt("anioEvento"), rs.getInt("estado")));
        }
        rs.close();
        ps.close();
        return lista;
    }

    @Override
    public List<Evento> listarEventoxNombre(String nombre) throws Exception {
        List<Evento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_LISTAR_X_NOMNRES_EVENTOS);
        ps.setString(1, nombre+"%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Evento(rs.getLong("idEvento"), rs.getString("descripcion"),
                    rs.getDate("fechaEvento").toLocalDate(), rs.getTimestamp("horaEvento").toLocalDateTime(),
                    rs.getInt("anioEvento"), rs.getInt("estado")));
        }
        rs.close();
        ps.close();
        return lista;
    }
}
