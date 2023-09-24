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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.gob.sunat.gestion.asistencias.model.dao.EventoDao;
import pe.gob.sunat.gestion.asistencias.model.entities.AsistenciaEvento;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;
import pe.gob.sunat.gestion.asistencias.model.entities.EventoCombo;
import pe.gob.sunat.gestion.asistencias.model.entities.EventoDashboard;

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
    private String Q_BUSCAR_EVENTOS_DASHBOARD1= "select asi.idAsistencia as id,CONCAT(p.nombres, ' ', p.apellidoPaterno ,' ' , p.apellidoMaterno)  as nombres,\n" +
                                                "e.descripcion ,  c.nombreCondominio , asi.fechaHoraAsistencia \n" +
                                                "from asistencia asi , propietario p , evento e, condominio c \n" +
                                                "where asi.idPropietario = p.idPropietario and\n" +
                                                "	 asi.idEvento = e.idEvento and\n" +
                                                "     p.idCondominio = c.idCondominio AND  UPPER(CONCAT(p.nombres, ' ', p.apellidoPaterno ,' ' , p.apellidoMaterno))  like ? AND e.idEvento = ?";
    
    private String Q_BUSCAR_EVENTOS_DASHBOARD2= "select asi.idAsistencia as id,CONCAT(p.nombres, ' ', p.apellidoPaterno ,' ' , p.apellidoMaterno)  as nombres,\n" +
                                                "e.descripcion ,  c.nombreCondominio , asi.fechaHoraAsistencia \n" +
                                                "from asistencia asi , propietario p , evento e, condominio c \n" +
                                                "where asi.idPropietario = p.idPropietario and\n" +
                                                "	 asi.idEvento = e.idEvento and\n" +
                                                "     p.idCondominio = c.idCondominio AND  p.dni = ? AND e.idEvento = ?";
    private String Q_BUSCAR_EVENTOS_DASHBOARD3= "select asi.idAsistencia as id,CONCAT(p.nombres, ' ', p.apellidoPaterno ,' ' , p.apellidoMaterno)  as nombres,\n" +
                                                "e.descripcion ,  c.nombreCondominio , asi.fechaHoraAsistencia \n" +
                                                "from asistencia asi , propietario p , evento e, condominio c \n" +
                                                "where asi.idPropietario = p.idPropietario and\n" +
                                                "	 asi.idEvento = e.idEvento and\n" +
                                                "     p.idCondominio = c.idCondominio AND  p.idPropietario = ? AND e.idEvento = ?";

    
    private String Q_BUSCAR_ASISTENES = "SELECT count(*) as cantidad ,capacidad FROM asistencia asi, evento ev \n" +
                                            " where  asi.idEvento = ev.idEvento and  ev.idEvento = ? group by ev.idEvento";
    
     private String Q_BUSCAR_ASISTENESCAPACIDAD = " SELECT  capacidad FROM  evento   where idEvento = ?";
     
    public EventoDaoImpl(Connection cn) {
        this.cn = cn;
    }

    @Override
    public List<AsistenciaEvento> listarAsistents(Long idEvento) throws Exception {
        List<AsistenciaEvento> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_BUSCAR_ASISTENES);
        ps.setLong(1, idEvento);
        ResultSet rs = ps.executeQuery();
        
        int rscount = 0;

        while (rs.next()) {
            lista.add(new AsistenciaEvento(rs.getLong("cantidad"), rs.getLong("capacidad")));
            rscount ++;
        }
        rs.close();
//        ps.close();
        System.out.println("cantidad de resultados  1er " + rscount);
            
        if(rscount == 0 )  {
            PreparedStatement ps2 = cn.prepareStatement(Q_BUSCAR_ASISTENESCAPACIDAD);
            ps2.setLong(1, idEvento);
            ResultSet rs2 = ps2.executeQuery();
            
            while (rs2.next()) {
                System.out.println("entro  " + rs2.getLong("capacidad"));
                lista.add(new AsistenciaEvento(0L, rs2.getLong("capacidad")));
            }
        rs2.close();
//        ps2.close();    
            
        }  
        
        

        

        return lista;
    }
    
    
    @Override
    public List<EventoDashboard> listarEventoDashboard(int tipoC, String txtbusqueda, Long idEvento) throws Exception {
        List<EventoDashboard> lista = new ArrayList<>();
        PreparedStatement ps = null;
         
        if (tipoC == 1){ // busqueda por nombres y apellidos
            
            
            ps = cn.prepareStatement(Q_BUSCAR_EVENTOS_DASHBOARD1);
   
            ps.setString(1, "%" + txtbusqueda.toUpperCase() + "%");
            ps.setLong(2, idEvento);
            
             
             
             System.out.println(Q_BUSCAR_EVENTOS_DASHBOARD1);
             
        }else if (tipoC == 2){ // busqueda por dni
             ps = cn.prepareStatement(Q_BUSCAR_EVENTOS_DASHBOARD2);
             ps.setString(1, txtbusqueda);
             ps.setLong(2, idEvento);

             
             System.out.println(Q_BUSCAR_EVENTOS_DASHBOARD1);  

        }else if (tipoC == 3){ // busqueda por id propietario
             ps = cn.prepareStatement(Q_BUSCAR_EVENTOS_DASHBOARD3);
             ps.setString(1, txtbusqueda);
             ps.setLong(2, idEvento);
             
             System.out.println(Q_BUSCAR_EVENTOS_DASHBOARD1);
           
        }
        
       
        
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new EventoDashboard(
                    rs.getLong("id"), rs.getString("descripcion"),
                    rs.getString("nombres"),rs.getTimestamp("fechaHoraAsistencia").toLocalDateTime(),
                            rs.getString("nombreCondominio")));
        }
        rs.close();
        return lista;
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
        return lista;
    }

    @Override
    public List<EventoCombo> listarEventosActivos2() throws Exception {
        List<EventoCombo> lista = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(Q_LISTAR_EVENTOS_ACTIVOS);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new EventoCombo(rs.getLong("idEvento"), rs.getString("descripcion")));
        }
        rs.close();
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
            if (cn != null) {
//                    cn.close();
            }
            if (pstmt != null) {
//                    pstmt.close();
            }
        }
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
            pstmt.setLong(5, evento.getIdEvento());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            if (cn != null) {
//                    cn.close();
            }
            if (pstmt != null) {
//                    pstmt.close();
            }
        }
    }

    @Override
    public void desactivarEvento(Long idEvento) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(Q_DESACTIVAR_EVENTOS_IDEVENTO);
            pstmt.setLong(1, idEvento);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            if (cn != null) {
//                    cn.close();
            }
            if (pstmt != null) {
//                    pstmt.close();
            }
        }
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
        return lista;
    }
}
