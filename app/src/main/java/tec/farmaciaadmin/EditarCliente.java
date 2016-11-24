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
import tec.clases.UsuarioCliente;

public class EditarCliente extends AppCompatActivity {

    EditText nombre;
    EditText username;
    EditText telefono;
    EditText passw;
    Button btnGuardar;
    Button btnCancelar;
    String oldId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);
        nombre = (EditText)findViewById(R.id.editarClienteNombre);
        username = (EditText)findViewById(R.id.editarClienteUsername);
        telefono = (EditText)findViewById(R.id.editarClienteTelefono);
        passw = (EditText)findViewById(R.id.editarClientePassw);

        nombre.setText(getIntent().getExtras().getString("nombre"));
        username.setText(getIntent().getExtras().getString("username"));
        oldId = username.getText().toString();
        telefono.setText(getIntent().getExtras().getString("telefono"));
        passw.setText(getIntent().getExtras().getString("passw"));

        btnGuardar = (Button)findViewById(R.id.editarClienteBtnGuardar);
        btnCancelar = (Button)findViewById(R.id.editarClienteBtnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrar();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = nombre.getText().toString();
                String p = passw.getText().toString();
                String t = telefono.getText().toString();
                String u = username.getText().toString();
                editar(n,oldId,u,t,p);
            }
        });
    }

    private void cerrar(){
        this.finish();
    }


    private void editar(String nombre, String username , String username2, String telefono, String passw){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<UsuarioCliente> call = retrofitClient.editarCliente(nombre, username, username2, telefono, passw);
        call.enqueue(new Callback<UsuarioCliente>() {
            @Override
            public void onResponse(Response<UsuarioCliente> response, Retrofit retrofit) {
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
