package tec.farmaciaadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import tec.clases.Medicina;
import tec.Retrofit.RetrofitClient;

public class MainActivity extends AppCompatActivity {

    ListView medicinas;
    //String [] datos = {"Ibuprofeno" ,"Acetaminofen","Panadol", "Alka Ad", "Curitas", "Vitamina C"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medicinas = (ListView)findViewById(R.id.listaProductos);
        //medicinas.setAdapter(null);
        this.registerForContextMenu(medicinas);

        //Evento de los items del listView
        medicinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                //PopUp Menu
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());

                //Acciones del menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.popEditar){
                            Intent intent = new Intent(getApplicationContext(), EditarProducto.class);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.popEliminar){
                            //Accion eliminar
                            getProducts();
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }

    //Configuracion del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProducts();
    }

    //Acciones del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.agregar){
            Intent intent = new Intent(getApplicationContext(), AgregarProducto.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.verUsuarios){
            Intent intent = new Intent(getApplicationContext(), VerClientes.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void llenarListView(String [] datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        medicinas.setAdapter(adapter);
    }

    private void getProducts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<List<Medicina>> call = retrofitClient.cargarMedicinas();

        call.enqueue(new Callback<List<Medicina>>() {
            @Override
            public void onResponse(Response<List<Medicina>> response, Retrofit retrofit) {
                List<Medicina> medicinas_data = response.body();
                String [] datos = new String[medicinas_data.size()];

                for(int i = 0; i < medicinas_data.size(); i++){
                    datos[i] = medicinas_data.get(i).getNombreMedicamento();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, datos);
                medicinas.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "ERROR MAMÓN", Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

}
