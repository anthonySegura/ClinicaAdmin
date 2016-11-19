package tec.farmaciaadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarProducto extends AppCompatActivity {

    EditText descripcion;
    EditText precio;
    EditText nombre;
    Button guardar;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        descripcion = (EditText)findViewById(R.id.editarDescripcion);
        precio = (EditText)findViewById(R.id.editarPrecio);
        nombre = (EditText)findViewById(R.id.editarNombre);
        guardar = (Button)findViewById(R.id.btnEditarGuardar);
        cancelar = (Button)findViewById(R.id.btnEditarCancelar);

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
