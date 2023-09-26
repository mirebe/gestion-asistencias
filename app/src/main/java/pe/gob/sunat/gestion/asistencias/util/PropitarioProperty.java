/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pe.gob.sunat.gestion.asistencias.model.entities.Propietario;

/**
 * private Integer idPropietario; private Integer idCondominio; private String
 * nombres; private String apellidoPaterno; private String apellidoMaterno;
 * private Integer idGenero; private String correo; private String dni; private
 * Integer dpto; private Integer estado;
 *
 * @author mireb
 */
public class PropitarioProperty {

    private final StringProperty idPropietario;
    private final StringProperty nombres;
    private final StringProperty apellidoPaterno;
    private final StringProperty apellidoMaterno;
    private final StringProperty correo;
    private final StringProperty dni;
    private final StringProperty dpto;

    private StringProperty estado = new SimpleStringProperty("1");
    private StringProperty idGenero = new SimpleStringProperty("1");
    
    private Propietario propietario;

    public PropitarioProperty(Long idPropietario, String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String dni, Integer dpto, Integer estado, Integer idGenero) {
        this.idPropietario = new SimpleStringProperty(idPropietario.toString());
        this.nombres = new SimpleStringProperty(nombres);
        this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
        this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
        this.correo = new SimpleStringProperty(correo);
        this.dni = new SimpleStringProperty(dni);
        this.dpto = new SimpleStringProperty(dpto.toString());
        this.estado = new SimpleStringProperty(estado.toString());
        this.idGenero = new SimpleStringProperty(idGenero.toString());
        propietario = new Propietario(idPropietario,1,nombres,apellidoPaterno,apellidoMaterno,idGenero,correo,dni,dpto,estado);
    }


    
    
    public Propietario getPropietario() {
        return propietario;
    }
   
    public String getIdPropietario() {
        return idPropietario.getValue();
    }
    
    public String getNombres() {
        return nombres.getValue();
    }
    
    public String getApellidoPaterno() {
        return apellidoPaterno.getValue();
    }
        
    public String getApellidoMaterno() {
        return apellidoMaterno.getValue();
    }
   
    public String getCorreo() {
        return correo.getValue();
    }
    
    public String getDNI() {
        return dni.getValue();
    }
    
    public String getDpto() {
        return dpto.getValue();
    }

    public String getEstado() {
        return estado.getValue();
    }
    
    public String getGenero() {
        return idGenero.getValue();
    }

}
