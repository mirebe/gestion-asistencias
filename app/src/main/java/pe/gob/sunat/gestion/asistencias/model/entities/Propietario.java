/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.entities;

/**
 *
 * @author mireb
 */
public class Propietario {
    
    private Integer idPropietario;
    private Integer idCondominio;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer idGenero;
    private String correo;
    private String dni;
    private Integer dpto;
    private Integer estado;

    public Propietario() {
    }

    public Propietario(Integer idPropietario, Integer idCondominio, String nombres, String apellidoPaterno, String apellidoMaterno, Integer idGenero, String correo, String dni, Integer dpto, Integer estado) {
        this.idPropietario = idPropietario;
        this.idCondominio = idCondominio;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idGenero = idGenero;
        this.correo = correo;
        this.dni = dni;
        this.dpto = dpto;
        this.estado = estado;
    }

    public String getNombresCompleto(){
        return ("" + this.apellidoPaterno + " " +this.apellidoMaterno + " " + this.getNombres()).trim();
    }
    
    public Integer getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Integer getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(Integer idCondominio) {
        this.idCondominio = idCondominio;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getDpto() {
        return dpto;
    }

    public void setDpto(Integer dpto) {
        this.dpto = dpto;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    
}
