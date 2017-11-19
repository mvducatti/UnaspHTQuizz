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

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Italo on 18/10/2017.
 */

public class UsuarioAdapter extends ArrayAdapter<DBQuizz> {
    private Context context;
    private ArrayList<DBQuizz> lista;

    public UsuarioAdapter(Context context, ArrayList<DBQuizz> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final DBQuizz itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista, null);
        final View layout = convertView;

        TextView textViewPergunta = convertView.findViewById(R.id.textViewPergunta);
        textViewPergunta.setText(itemPosicao.getPergunta());

        TextView textViewOpt1 = convertView.findViewById(R.id.textViewOpt1);
        textViewOpt1.setText(itemPosicao.getOpt1());

        TextView textViewOpt2 = convertView.findViewById(R.id.textViewOpt2);
        textViewOpt2.setText(itemPosicao.getOpt2());

        TextView textViewOptCerta = convertView.findViewById(R.id.textViewOptCerta);
        textViewOptCerta.setText(itemPosicao.getOptCerta());

        Button button = (Button) convertView.findViewById(R.id.buttonEditar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CadastrarQuizz.class);
                intent.putExtra("pergunta", itemPosicao.getPergunta());
                intent.putExtra("Opção 1", itemPosicao.getOpt1());
                intent.putExtra("Opção 2", itemPosicao.getOpt2());
                intent.putExtra("Resposta Correta", itemPosicao.getOptCerta());
                intent.putExtra("id", itemPosicao.getId());
                context.startActivity(intent);
            }
        });
        Button buttonDeletar = (Button) convertView.findViewById(R.id.buttonApagar);
        buttonDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    itemPosicao.apagar();
                } catch (Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT);
                }
                if (itemPosicao._status)
                    layout.setVisibility(View.GONE);
                else {
                    Toast.makeText(context, itemPosicao._mensagem, Toast.LENGTH_SHORT);
                }
            }
        });
        return convertView;
    }
}
