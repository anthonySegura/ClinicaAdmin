package tec.farmaciaadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.app.ProgressDialog;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import tec.Retrofit.RequestResponse;
import tec.Retrofit.RetrofitClient;
import tec.clases.Administrador;
import tec.clases.UsuarioCliente;

public class NuevoUsuario extends AppCompatActivity {

    Switch isAdmin;
    EditText nombre;
    EditText passw;
    EditText telefono;
    EditText username;
    EditText cedula;
    EditText departamento;
    EditText salario;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.Espere));
        pDialog.setCancelable(false);
        nombre = (EditText)findViewById(R.id.nuevoClienteNombre);
        passw = (EditText)findViewById(R.id.nuevoClientePassw);
        telefono = (EditText)findViewById(R.id.nuevoClienteTelefono);
        username = (EditText)findViewById(R.id.nuevoClienteUsername);
        cedula = (EditText)findViewById(R.id.nuevoAdmiCedula);
        departamento = (EditText)findViewById(R.id.nuevoAdminDepartamento);
        salario = (EditText)findViewById(R.id.nuevoAdminSalario);
        isAdmin = (Switch)findViewById(R.id.switchNuevoUsuario);

        isAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cedula.setVisibility(View.VISIBLE);
                    departamento.setVisibility(View.VISIBLE);
                    salario.setVisibility(View.VISIBLE);
                }
                else{
                    cedula.setVisibility(View.GONE);
                    departamento.setVisibility(View.GONE);
                    salario.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_nuevo_usuario, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.cancelarNuevoUsuario){
            cerrar();
        }

        else if (id == R.id.guardarUsuario){
            comprobarCampos();
        }

        return super.onOptionsItemSelected(item);
    }

    private void comprobarCampos() {

        String n = nombre.getText().toString();
        String t = telefono.getText().toString();
        String u = username.getText().toString();
        String p = passw.getText().toString();
        String c = cedula.getText().toString();
        String d = departamento.getText().toString();
        String s = salario.getText().toString();

        if(!isAdmin.isChecked()){
            if(n.equals("") || t.equals("") || u.equals("") || p.equals("")){

                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.Faltan_Datos), Toast.LENGTH_LONG);
                toast.show();
            }

            else {
                nuevoUsuario(n,u,t,p);

                cerrar();
            }
        }
        else{
            if(n.equals("") || t.equals("") || u.equals("") || p.equals("") || c.equals("")
                    || d.equals("") || s.equals("")){
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.Faltan_Datos), Toast.LENGTH_LONG);
                toast.show();

            }
            else{
                validarCedula(c);

            }
        }
    }

    private void cerrar(){
        this.finish();
    }


    private void validarCedula(final String cedula){
        pDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<RequestResponse> call = retrofitClient.comprobarCedula(cedula);

        call.enqueue(new Callback<RequestResponse>() {
            @Override
            public void onResponse(Response<RequestResponse> response, Retrofit retrofit) {

                RequestResponse result = response.body();

                if (result.getResponse() == 0){
                    String n = nombre.getText().toString();
                    String t = telefono.getText().toString();
                    String u = username.getText().toString();
                    String p = passw.getText().toString();
                    String d = departamento.getText().toString();
                    String s = salario.getText().toString();
                    nuevoAdmin(n,u,t,p,cedula,d,s);
                    pDialog.hide();
                    cerrar();
                }
                else{
                    pDialog.hide();
                    Toast.makeText(getApplicationContext(), getString(R.string.Cedula_Repetidad), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                pDialog.hide();
                Toast.makeText(getApplicationContext(), getString(R.string.error_conexion), Toast.LENGTH_SHORT).show();
            }
        });
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

            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(getApplication(), getString(R.string.error_conexion), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void nuevoAdmin(String nombre, String username, String telefono, String passw, String cedula,
                            String departamento, String salario){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<Administrador> call = retrofitClient.agregarAdmin(nombre, username,telefono,passw, cedula, departamento, salario);

        call.enqueue(new Callback<Administrador>() {
            @Override
            public void onResponse(Response<Administrador> response, Retrofit retrofit) {
                System.out.println("OK");
                Toast toast = Toast.makeText(getApplication(), getString(R.string.Nuevo_Admin), Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(getApplication(), getString(R.string.error_conexion), Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }
}
