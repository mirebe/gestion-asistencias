module pe.gob.sunat.gestion.asistencias {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires fontawesomefx;
    opens pe.gob.sunat.gestion.asistencias.controller to javafx.fxml;
    exports pe.gob.sunat.gestion.asistencias;
}
