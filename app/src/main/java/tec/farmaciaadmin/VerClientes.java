package tec.farmaciaadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import tec.Retrofit.RetrofitClient;
import tec.clases.UsuarioCliente;

public class VerClientes extends AppCompatActivity {

    ListView clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_clientes);
        getClients();
        clientes = (ListView)findViewById(R.id.clientes);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getClients();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_ver_usuarios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.nuevoCliente){
            Intent intent = new Intent(getApplicationContext(), NuevoUsuario.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ClientAdapter getAdapter(ArrayList<UsuarioCliente> data){
        ClientAdapter adapter = new ClientAdapter(this, data);
        return adapter;
    }

    private void getClients(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<List<UsuarioCliente>> call = retrofitClient.cargarClientes();

        call.enqueue(new Callback<List<UsuarioCliente>>() {
            @Override
            public void onResponse(Response<List<UsuarioCliente>> response, Retrofit retrofit) {

                List<UsuarioCliente> clientes_data = response.body();
                ArrayList<UsuarioCliente> data = new ArrayList<UsuarioCliente>();

                for (UsuarioCliente usr : clientes_data){
                    UsuarioCliente newUsr = new UsuarioCliente(usr.getUsername(), usr.getNombre(), " ", usr.getTelefono());
                    newUsr.setFechaRegistro(usr.getFechaRegistro());
                    newUsr.setUltimoLogin(usr.getUltimoLogin());
                    data.add(newUsr);
                }

                ClientAdapter adapter = getAdapter(data);
                clientes.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(getApplication(), "Error Mamón", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
