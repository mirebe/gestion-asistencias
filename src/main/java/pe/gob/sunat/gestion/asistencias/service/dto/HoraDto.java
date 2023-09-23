/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

/**
 *
 * @author mireb
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class HoraDto {
    String datetime;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    public LocalDateTime obtenerHora(){
        //DateTimeFormatter newYorkDateFormatter = DateTimeFormatter.ofPattern(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String partHora[] = datetime.split("\\.");
        return LocalDateTime.parse(partHora[0]);
    }
    
}
