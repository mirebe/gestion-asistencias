module pe.gob.sunat.gestion.asistencias {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires fontawesomefx;
    requires retrofit;
    requires converter.scalars;
    requires converter.gson;
    requires converter.jackson;
    
    //requires retrofit2;
    //requires retrofit2.converter.gson;
    //requires retrofit2.converter.scalars;
    //requires retrofit2.converter.jackson;
    requires jackson.annotations;
   
    opens pe.gob.sunat.gestion.asistencias;
    opens pe.gob.sunat.gestion.asistencias.service.client;
    opens pe.gob.sunat.gestion.asistencias.service.dto;
    opens pe.gob.sunat.gestion.asistencias.controller to javafx.fxml;
    exports pe.gob.sunat.gestion.asistencias;
}
