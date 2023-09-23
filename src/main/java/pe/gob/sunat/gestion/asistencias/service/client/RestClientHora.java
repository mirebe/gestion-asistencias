/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.service.client;

import java.time.LocalDateTime;
import pe.gob.sunat.gestion.asistencias.service.dto.HoraDto;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import pe.gob.sunat.gestion.asistencias.service.ClientHoraInterface;

/**
 *
 * @author mireb
 */
public class RestClientHora {
    
    public LocalDateTime obtenerHoraWoldTimeApi() throws Exception{
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://worldtimeapi.org")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        ClientHoraInterface service = retrofit.create(ClientHoraInterface.class);
        Call<HoraDto> call = service.obtieneHoraLima();
        Response<HoraDto> response = call.execute();
        System.out.println("[Code: " +  response.code() + "]");                
        if(response.isSuccessful()){//si la peticion se completo con exito
            return response.body().obtenerHora();
        }else{//La peticion se realizo, pero ocurrio un error
            System.out.println("ERROR: " +  response.message() );
            throw new Exception("ERROR: " +  response.message());
        } 
    }
    
    /*public static void main(String[] args) {
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://worldtimeapi.org")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        ClientHoraInterface service = retrofit.create(ClientHoraInterface.class);
        
        Call<HoraDto> call = service.obtieneHoraLima();
        call.enqueue(new Callback<HoraDto>() {
            @Override
            public void onFailure(Call<HoraDto> call, Throwable t) {
               System.out.println("Network Error :: " + t.getLocalizedMessage());
            }
            
           // @Override
            public void onResponse(Call<HoraDto> call, Response<HoraDto> response) {
                //Codigo de respuesta
                System.out.println("[Code: " +  response.code() + "]");                
                if(response.isSuccessful()){//si la peticion se completo con exito
                    //ArticuloResponse articulo = response.body();
                    System.out.println("Response:\n"+response.body().obtenerHora());
                }else{//La peticion se realizo, pero ocurrio un error
                    System.out.println("ERROR: " +  response.message() );
                }    
            }
        });        
    }*/
}
