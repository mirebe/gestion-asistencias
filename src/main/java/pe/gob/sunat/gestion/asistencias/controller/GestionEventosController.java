/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import pe.gob.sunat.gestion.asistencias.model.entities.Evento;
import pe.gob.sunat.gestion.asistencias.service.EventoService;
import pe.gob.sunat.gestion.asistencias.service.impl.EventoServiceImpl;

/**
 *
 * @author mireb
 */
public class GestionEventosController implements Initializable {

    private final EventoService eventoService;

    public GestionEventosController() {
        System.out.println("GestionEventosController.<constructor>()");
        eventoService = new EventoServiceImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarDatosEnTabla();
    }

    private void llenarDatosEnTabla() {
        try {
            List<Evento> list = eventoService.listarEvento();
        } catch (Exception e) {
            System.out.println("Error=" + e.getMessage());
            e.printStackTrace();
        }

    }

}
