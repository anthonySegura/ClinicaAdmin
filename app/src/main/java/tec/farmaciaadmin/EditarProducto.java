package tec.farmaciaadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class EditarProducto extends AppCompatActivity {

    EditText descripcion;
    EditText precio;
    EditText nombre;
    EditText cantidad;
    Button guardar;
    Button cancelar;

    String oldId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        descripcion = (EditText)findViewById(R.id.editarDescripcion);
        precio = (EditText)findViewById(R.id.editarPrecio);
        nombre = (EditText)findViewById(R.id.editarNombre);
        cantidad = (EditText)findViewById(R.id.editarCantidad);
        guardar = (Button)findViewById(R.id.btnEditarGuardar);
        cancelar = (Button)findViewById(R.id.btnEditarCancelar);

        //Se cargan los datos del producto
        descripcion.setText(getIntent().getExtras().getString("descripcion"));
        String _cant = getIntent().getExtras().get("cantidad").toString();
        cantidad.setText(_cant);
        precio.setText(getIntent().getExtras().getString("precioUnidad"));
        nombre.setText(getIntent().getExtras().getString("nombreMedicamento"));
        oldId = nombre.getText().toString();
        //Eventos de los botones
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrar();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre2 = nombre.getText().toString();
                String _descripcion = descripcion.getText().toString();
                int _cantidad = Integer.parseInt(cantidad.getText().toString());
                String _precio = precio.getText().toString();

                actualizarProducto(oldId, nombre2, _descripcion, _cantidad, _precio);
            }
        });
    }

    private void cerrar(){
        this.finish();
    }


    /**
     * Realiza una llamada al WebService para modificar el producto
     *
     * @param nombre1
     * @param nombre2
     * @param descripcion
     * @param cantidad
     * @param precio
     */
    private void actualizarProducto(String nombre1, String nombre2, String descripcion, int cantidad, String precio){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<Medicina> call = retrofitClient.editarProducto(nombre1, nombre2, descripcion, cantidad, precio);

        call.enqueue(new Callback<Medicina>() {
            @Override
            public void onResponse(Response<Medicina> response, Retrofit retrofit) {
                System.out.println("Funciono");
                cerrar();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toas = Toast.makeText(getApplicationContext(),getString(R.string.error_conexion), Toast.LENGTH_LONG);
                toas.show();
            }
        });


    }
}
