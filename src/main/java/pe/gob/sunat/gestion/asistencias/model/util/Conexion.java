/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mireb
 */
public class Conexion {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String DB_NAME = "bdasistencia";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME), USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.err.println("Ocurrio un error "+ex.getMessage());
        }catch (ClassNotFoundException ex) {
            System.err.println("Ocurrio un error "+ex.getMessage());
        }
        return connection;
    }

}
