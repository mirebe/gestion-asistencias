/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.model.entities;

/**
 *
 * @author mireb
 */
public class Usuario {
    
    private String codUsu;
    private String clave;
    private String nombre;
    private String perfilUsu;

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfilUsu() {
        return perfilUsu;
    }

    public void setPerfilUsu(String perfilUsu) {
        this.perfilUsu = perfilUsu;
    }
    
    
}
