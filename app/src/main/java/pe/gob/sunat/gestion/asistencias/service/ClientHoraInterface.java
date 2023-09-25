/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service;

import pe.gob.sunat.gestion.asistencias.service.dto.HoraDto;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 *
 * @author mireb
 */
public interface ClientHoraInterface {
    @GET("/api/timezone/America/Lima")
    Call<HoraDto> obtieneHoraLima();
}
