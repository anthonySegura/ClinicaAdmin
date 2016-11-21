package tec.Retrofit;

import java.util.List;


import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import tec.clases.Medicina;

/**
 * Created by anthony on 20/11/16.
 */

public interface RetrofitClient {

    static final String URL_BASE = "http://10.0.2.2:8080/";

    @GET("GetAllProducts")
    Call<List<Medicina>> cargarMedicinas();


    @GET("BorrarMedicamento")
    Call<String> borrarMedicamento(@Query("nombre") String id);

    @FormUrlEncoded
    @POST("CreateProducto")
    Call<Medicina> agregarMedicamento(
            @Field("nombre") String nombre,
            @Field("descripcion") String descripcion,
            @Field("cantidad") int cantidad,
            @Field("precio") String precio);



}
