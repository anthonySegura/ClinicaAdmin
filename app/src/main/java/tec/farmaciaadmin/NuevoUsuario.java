package tec.farmaciaadmin;

import android.provider.Settings;
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
import tec.clases.UsuarioCliente;

public class NuevoUsuario extends AppCompatActivity {

    EditText nombre;
    EditText passw;
    EditText telefono;
    EditText username;
    Button guardar;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        nombre = (EditText)findViewById(R.id.nuevoClienteNombre);
        passw = (EditText)findViewById(R.id.nuevoClientePassw);
        telefono = (EditText)findViewById(R.id.nuevoClienteTelefono);
        username = (EditText)findViewById(R.id.nuevoClienteUsername);
        guardar = (Button)findViewById(R.id.nuevoClienteBtnGuardar);
        cancelar = (Button)findViewById(R.id.nuevoClienteBtnCancelar);

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
                String p = passw.getText().toString();
                String t = telefono.getText().toString();
                String u = username.getText().toString();

                nuevoUsuario(n,u,t,p);
            }
        });
    }

    private void cerrar(){
        this.finish();
    }

    private void nuevoUsuario(String nombre, String username, String telefono, String passw){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<UsuarioCliente> call = retrofitClient.agregarCliente(nombre, username,telefono,passw);

        call.enqueue(new Callback<UsuarioCliente>() {
            @Override
            public void onResponse(Response<UsuarioCliente> response, Retrofit retrofit) {
                System.out.println("OK");
                cerrar();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(getApplication(), getString(R.string.error_conexion), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
