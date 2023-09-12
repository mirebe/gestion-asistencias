package pe.gob.sunat.gestion.asistencias;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.stage.StageStyle;
import pe.gob.sunat.gestion.asistencias.controller.LoginController;
import pe.gob.sunat.gestion.asistencias.util.FXMLArchivo;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLArchivo<LoginController> loginController = new FXMLArchivo("VistaLogin",this);
        Scene scene = new Scene(loginController.getRoot());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        
        loginController.getController().init(stage,scene);        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
   
}