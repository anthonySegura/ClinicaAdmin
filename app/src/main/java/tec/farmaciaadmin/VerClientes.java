package tec.farmaciaadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import tec.clases.UsuarioCliente;

public class VerClientes extends AppCompatActivity {


    //Clientes de prueba
    UsuarioCliente c1 = new UsuarioCliente("Nombre Fulano", " ", "123456");
    ArrayList<UsuarioCliente> arrayUsuarioClientes = new ArrayList<>();

    ListView clientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_clientes);
        c1.setFechaRegistro("Fecha de registro 10/10/16");
        c1.setUltimoLogin("Ultimo Login 18/11/16");
        arrayUsuarioClientes.add(c1);
        clientes = (ListView)findViewById(R.id.clientes);
        myAdapter adapter = new myAdapter(this, arrayUsuarioClientes);
        clientes.setAdapter(adapter);
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
}
