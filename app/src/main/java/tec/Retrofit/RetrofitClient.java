package tec.Retrofit;

import java.util.List;


import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import tec.clases.Medicina;
import tec.clases.UsuarioCliente;

/**
 * Created by anthony on 20/11/16.
 */

public interface RetrofitClient {

    static final String URL_BASE = "http://ec2-35-163-108-171.us-west-2.compute.amazonaws.com/";

    //Metodos GET

    @GET("GetAllProducts")
    Call<List<Medicina>> cargarMedicinas();


    @GET("BorrarMedicamento")
    Call<String> borrarMedicamento(@Query("nombre") String id);


    @GET("GetAllClients")
    Call<List<UsuarioCliente>> cargarClientes();

    @GET("BorrarUsuario")
    Call<String> borrarUsuario(@Query("username") String id);


    //Metodos POST

    @FormUrlEncoded
    @POST("CreateProducto")
    Call<Medicina> agregarMedicamento(
            @Field("nombre") String nombre,
            @Field("descripcion") String descripcion,
            @Field("cantidad") int cantidad,
            @Field("precio") String precio);


    @FormUrlEncoded
    @POST("CreateClient")
    Call<UsuarioCliente> agregarCliente(
            @Field("nombre") String nombre,
            @Field("username") String username,
            @Field("telefono") String telefono,
            @Field("passw") String passw
    );


    @FormUrlEncoded
    @POST("UpdateClient")
    Call<UsuarioCliente> editarCliente(
            @Field("nombre") String nombre,
            @Field("username") String username,
            @Field("username2") String username2,
            @Field("telefono") String telefono,
            @Field("passw") String passw
    );

    @FormUrlEncoded
    @POST("UpdateProduct")
    Call<Medicina> editarProducto(
            @Field("nombre1") String nombre1,
            @Field("nombre2") String nombre2,
            @Field("descripcion") String descripcion,
            @Field("cantidad") int cantidad,
            @Field("precio") String precio
    );

}
