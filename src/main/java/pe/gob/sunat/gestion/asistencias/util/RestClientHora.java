/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.gestion.asistencias.util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 *
 * @author mireb
 */
public class RestClientHora {
    public static void main(String[] args) {
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://worldtimeapi.org/api/timezone/America/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        ClientMethod service = retrofit.create(ClientMethod.class);
        Call<HoraResponse> call = service.obtieneHoraLima();
        call.enqueue(new Callback<HoraResponse>() {
            @Override
            public void onFailure(Call<HoraResponse> call, Throwable t) {
               System.out.println("Network Error :: " + t.getLocalizedMessage());
            }

            /**
             * La respuesta del servidor
             */
            @Override
            public void onResponse(Call<HoraResponse> call, Response<HoraResponse> response) {
                //Codigo de respuesta
                System.out.println("[Code: " +  response.code() + "]");                
                if(response.isSuccessful()){//si la peticion se completo con exito
                    //ArticuloResponse articulo = response.body();
                    System.out.println("Response:\n"+response.body());
                }else{//La peticion se realizo, pero ocurrio un error
                    System.out.println("ERROR: " +  response.message() );
                }
                
            }
        });
        
    }
}
