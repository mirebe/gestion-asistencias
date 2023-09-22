/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.util;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 *
 * @author mireb
 */
public interface ClientMethod {
    @GET("/Lima")
    Call<HoraResponse> obtieneHoraLima();
}
