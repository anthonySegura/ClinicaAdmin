package tec.farmaciaadmin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import tec.clases.UsuarioCliente;

public class myAdapter extends ArrayAdapter<UsuarioCliente> {

    private final Context context;
    private final ArrayList<UsuarioCliente> itemsArrayList;

    public myAdapter(Context context, ArrayList<UsuarioCliente> itemsArrayList) {

        super(context, R.layout.list_item, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View item = inflater.inflate(R.layout.list_item, parent, false);

        TextView nombre = (TextView) item.findViewById(R.id.nameText);
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
                confirmar_eliminar();
            }
        });

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamar(itemsArrayList.get(position).getTelefono());
            }
        });

        return item;
    }

    private void confirmar_eliminar() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Desea eliminar el cliente ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

                //Eliminar del WS

            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {


            }
        });
        dialogo1.show();
    }

    private void llamar(String numero){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + numero));
        context.startActivity(callIntent);
    }
}