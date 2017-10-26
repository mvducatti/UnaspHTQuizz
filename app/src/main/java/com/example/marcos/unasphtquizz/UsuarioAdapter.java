package com.example.marcos.unasphtquizz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Italo on 18/10/2017.
 */

public class UsuarioAdapter extends ArrayAdapter<Usuario> {
    private Context context;
    private ArrayList<Usuario> lista;

    public UsuarioAdapter(Context context, ArrayList<Usuario> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Usuario itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista, null);
        final View layout = convertView;

        TextView textView = (TextView) convertView.findViewById(R.id.textViewNome);
        textView.setText(itemPosicao.getNome());

        TextView textViewEmail = (TextView) convertView.findViewById(R.id.textViewEmail);
        textView.setText(itemPosicao.getEmail());

        TextView textViewTelefone = (TextView) convertView.findViewById(R.id.textViewTelefone);
        textView.setText(itemPosicao.getTelefone());

        Button button = (Button) convertView.findViewById(R.id.buttonEditar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NovoActivity.class);
                intent.putExtra("telefone", itemPosicao.getTelefone());
                intent.putExtra("telefone", itemPosicao.getNome());
                intent.putExtra("telefone", itemPosicao.getEmail());
                intent.putExtra("telefone", itemPosicao.getId());
                context.startActivity(intent);
            }
        });
        Button buttonDeletar = (Button) convertView.findViewById(R.id.buttonApagar);
        buttonDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemPosicao.apagar();
                if (itemPosicao._status)
                    layout.setVisibility(View.GONE);
                else {
                    Toast.makeText(context, itemPosicao.get_mensagem(), Toast.LENGTH_SHORT);
                }
            }
        });
        return convertView;
    }
}
