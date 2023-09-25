/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.util;

import java.util.concurrent.Callable;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author mireb
 */
public class ProcesosAsincronos {
    public Service Ejecutar(Callable evento){
        Service serv = new Service() {
            @Override
            protected Task createTask() {
                return new Task(){
                    @Override
                    protected Object call() throws Exception {
                       evento.call();
                       return null;
                    }
                };
            }
        };
        serv.restart();
        return serv;
    }
}
