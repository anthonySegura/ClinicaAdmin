package tec.farmaciaadmin;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import tec.Retrofit.RetrofitClient;
import tec.clases.UsuarioCliente;


/**
 * Adapter para el listView personalizado
 */
public class ClientAdapter extends ArrayAdapter<UsuarioCliente> {

    private final Context context;
    private final ArrayList<UsuarioCliente> itemsArrayList;

    public ClientAdapter(Context context, ArrayList<UsuarioCliente> itemsArrayList) {

        super(context, R.layout.list_item, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View item = inflater.inflate(R.layout.list_item, parent, false);

        final TextView nombre = (TextView) item.findViewById(R.id.nameText);
        TextView telefono = (TextView) item.findViewById(R.id.telefonoText);
        TextView registro = (TextView) item.findViewById(R.id.fechaRegistro);
        TextView ultimoLogin = (TextView) item.findViewById(R.id.ultimoLogin);
        ImageButton editar = (ImageButton) item.findViewById(R.id.bEditar);
        ImageButton eliminar = (ImageButton) item.findViewById(R.id.bEliminar);
        final ImageButton llamar = (ImageButton) item.findViewById(R.id.bLlamar);

        nombre.setText(itemsArrayList.get(position).getNombre());
        ultimoLogin.setText(itemsArrayList.get(position).getUltimoLogin());
        telefono.setText(itemsArrayList.get(position).getTelefono());
        registro.setText(itemsArrayList.get(position).getFechaRegistro());

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmar_eliminar(position);
            }
        });

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamar(itemsArrayList.get(position).getTelefono());
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                UsuarioCliente usr = itemsArrayList.get(position);

                bundle.putString("nombre", usr.getNombre());
                bundle.putString("username", usr.getUsername());
                bundle.putString("telefono", usr.getTelefono());
                bundle.putString("passw", usr.getPassword());
                abrirActivity(bundle);
            }
        });

        return item;
    }


    /**
     * Funcion del Dialog
     */
    private void confirmar_eliminar(final int position) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());
        dialogo.setTitle("Importante");
        dialogo.setMessage("¿ Desea eliminar el cliente ?");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //Eliminar del WS
                String _id = itemsArrayList.get(position).getUsername();
                eliminarCliente(_id);

            }
        });

        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo.show();
    }


    /**
     * Abre otra activity desde la actual
     */
    private void abrirActivity(Bundle bundle) {
        Intent intent = new Intent(getContext(), EditarCliente.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * Metodo para realizar una llamada telefonica
     */
    private void llamar(String numero) {
        int permiso=0;
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + numero));
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions((Activity) this.getContext(),
                    new String[]{Manifest.permission.READ_CONTACTS},
                    permiso);
            return;
        }
        context.startActivity(callIntent);
    }


    /**
     * Peticion al WS para eliminar el cliente indicado
     * @param id : username
     */
    private void eliminarCliente(String id){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<String> call = retrofitClient.borrarUsuario(id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                System.out.println("OK");
                ((VerClientes)context).reloadListViewData();
            }

            @Override
            public void onFailure(Throwable t) {

                Toast toast = Toast.makeText(getContext(),"Error", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}