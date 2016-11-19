package tec.farmaciaadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarProducto extends AppCompatActivity {

    EditText descripcion;
    EditText precio;
    EditText nombre;
    Button guardar;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        descripcion = (EditText)findViewById(R.id.agregarDescripcion);
        precio = (EditText)findViewById(R.id.agregarPrecio);
        nombre = (EditText)findViewById(R.id.agregarNombre);
        guardar = (Button)findViewById(R.id.agregarBtnGuardar);
        cancelar = (Button)findViewById(R.id.agregarBtnCancelar);


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrar();
            }
        });
    }


    private void cerrar(){
        this.finish();
    }
}
