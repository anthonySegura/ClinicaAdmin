package tec.farmaciaadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NuevoUsuario extends AppCompatActivity {

    EditText nombre;
    EditText passw;
    EditText telefono;
    Button guardar;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        nombre = (EditText)findViewById(R.id.nuevoClienteNombre);
        passw = (EditText)findViewById(R.id.nuevoClientePassw);
        telefono = (EditText)findViewById(R.id.nuevoClienteTelefono);
        guardar = (Button)findViewById(R.id.nuevoClienteBtnGuardar);
        cancelar = (Button)findViewById(R.id.nuevoClienteBtnCancelar);

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
