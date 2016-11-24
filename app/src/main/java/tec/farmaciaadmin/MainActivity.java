package tec.farmaciaadmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
    List<Medicina> medicinas_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medicinas = (ListView)findViewById(R.id.listaProductos);
        //medicinas.setAdapter(null);
        this.registerForContextMenu(medicinas);

        //Evento normal de los items del listView
        medicinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {

                //PopUp Menu
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());

                //Acciones del menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getItemId() == R.id.popEditar){

                            Bundle bundle = new Bundle();
                            Medicina med = medicinas_data.get(position);
                            bundle.putString("nombreMedicamento", med.getNombreMedicamento());
                            bundle.putString("descripcion", med.getDescripcion());
                            bundle.putInt("cantidad", med.getCantidad());
                            bundle.putString("precioUnidad", med.getPrecioUnidad());
                            Intent intent = new Intent(getApplicationContext(), EditarProducto.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.popEliminar){
                            confirmar_eliminar(position);
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });

        medicinas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Accion eliminar
                String _id = medicinas.getAdapter().getItem(position).toString();
                eliminarMedicina(_id);
                return true;
            }
        });
    }

    /**
     * Configuracion del menu
     */
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

    /**
     * Acciones del menu
     * @param item
     * @return
     */
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


    /**
     * Funcion del Dialog
     */
    private void confirmar_eliminar(final int position) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Desea eliminar el cliente ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //Accion eliminar
                String _id = medicinas_data.get(position).getNombreMedicamento();
                eliminarMedicina(_id);

            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }


    private ArrayAdapter<String> getAdapter(String [] datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        return adapter;
    }


    /**
     * Carga todas las medicinas desde el WS en el listView
     */
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
                medicinas_data = response.body();
                String [] datos = new String[medicinas_data.size()];

                for(int i = 0; i < medicinas_data.size(); i++){
                    datos[i] = medicinas_data.get(i).getNombreMedicamento();
                }

                medicinas.setAdapter(getAdapter(datos));
            }

            @Override
            public void onFailure(Throwable t) {

                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.error_conexion), Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

    /**
     * Borra la medicina del WS
     * @param id
     */
    private void eliminarMedicina(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<String> call = retrofitClient.borrarMedicamento(id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                getProducts();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.error_conexion) ,Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

}
