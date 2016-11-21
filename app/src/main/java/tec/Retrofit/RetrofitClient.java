package tec.Retrofit;

import java.util.List;


import retrofit.Call;
import retrofit.http.GET;
import tec.clases.Medicina;

/**
 * Created by anthony on 20/11/16.
 */

public interface RetrofitClient {

    static final String URL_BASE = "http://10.0.2.2:8080/";

    @GET("GetAllProducts")
    Call<List<Medicina>> cargarMedicinas();

}
