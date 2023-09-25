/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.util;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pe.gob.sunat.gestion.asistencias.App;

/**
 *
 * @author mireb
 * @param <T>
 */
public class FXMLArchivo <T> {
    private Parent root;
    private T controller;
    private App app;

    public FXMLArchivo(String fxml) throws IOException {
        this(fxml,null);
    }
    
    public FXMLArchivo(String fxml,App app) throws IOException {
        this.app = app;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/"+fxml + ".fxml"));
        this.root = fxmlLoader.load(); 
        this.controller = (T)fxmlLoader.getController();
    }
    /*public FXMLArchivo(Parent root, T controller) {
        this.root = root;
        this.controller = controller;
    }*/

    public Parent getRoot() {
        return root;
    }

    public T getController() {
        return controller;
    }

    public App getApp() {
        return app;
    }
    
    
}
