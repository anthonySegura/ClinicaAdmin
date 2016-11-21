package tec.farmaciaadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import tec.Retrofit.RetrofitClient;
import tec.clases.Medicina;

public class AgregarProducto extends AppCompatActivity {

    EditText descripcion;
    EditText precio;
    EditText nombre;
    EditText cantidad;
    Button guardar;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        descripcion = (EditText)findViewById(R.id.agregarDescripcion);
        precio = (EditText)findViewById(R.id.agregarPrecio);
        nombre = (EditText)findViewById(R.id.agregarNombre);
        cantidad = (EditText)findViewById(R.id.agregarCantidad);
        guardar = (Button)findViewById(R.id.agregarBtnGuardar);
        cancelar = (Button)findViewById(R.id.agregarBtnCancelar);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrar();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = nombre.getText().toString();
                String d = descripcion.getText().toString();
                String p = precio.getText().toString();
                int c = Integer.parseInt(cantidad.getText().toString());

                crearMedicamento(n,d,c,p);
                finish();
            }
        });
    }


    private void crearMedicamento(String nombre, String descripcion, int cantidad, String precio){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<Medicina> call = retrofitClient.agregarMedicamento(nombre, descripcion, cantidad, precio);

        call.enqueue(new Callback<Medicina>() {
            @Override
            public void onResponse(Response<Medicina> response, Retrofit retrofit) {
                System.out.println("OK");
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(getApplication(), getString(R.string.error_conexion), Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }

    private void cerrar(){
        this.finish();
    }
}
