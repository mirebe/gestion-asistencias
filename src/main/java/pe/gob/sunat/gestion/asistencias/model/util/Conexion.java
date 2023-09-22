/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *Instance una conexion de la base datos usando elpatron singleton
 * @author mireb
 */
public class Conexion {
    
    private String HOST = "127.0.0.1";
    private int PORT = 3306;
    private String DB_NAME = "asistencia_db";
    private String USERNAME = "root";
    private String PASSWORD = "mysql";
    private static Connection connection = null;
    
    private Conexion() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME), USERNAME, PASSWORD);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnect() {
        if(connection == null){
            new Conexion();
        }   
        return connection;
    }

}
