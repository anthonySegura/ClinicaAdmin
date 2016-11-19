package tec.farmaciaadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView medicinas;
    String [] datos = {"Ibuprofeno" ,"Acetaminofen","Panadol", "Alka Ad", "Curitas", "Vitamina C"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medicinas = (ListView)findViewById(R.id.listaProductos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        medicinas.setAdapter(adapter);
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

    //Acciones del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.agregar){
            Intent intent = new Intent(getApplicationContext(), AgregarProducto.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
