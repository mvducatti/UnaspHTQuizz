package com.example.marcos.unasphtquizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CadastrarQuizz extends AppCompatActivity {

    private DBQuizz DBQuizz;
    private EditText editTextPergunta;
    private EditText editTextOpt1;
    private EditText editTextOpt2;
    private EditText editTextOptCerta;
    private Spinner meuspinner;
    private ArrayList<Integer> arrayMateria;

    public String _mensagem;
    public boolean _status;

    public void exibirTexto(String titulo, String txt){
        AlertDialog alertDialog = new AlertDialog.Builder(CadastrarQuizz.this).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarquizz);

        DBQuizz = new DBQuizz();
        editTextPergunta = (EditText) findViewById(R.id.editTextPergunta);
        editTextOpt1 = (EditText) findViewById(R.id.editTextOpt1);
        editTextOpt2 = (EditText) findViewById(R.id.editTextOpt2);
        editTextOptCerta = (EditText) findViewById(R.id.editTextOptCerta);
        meuspinner = (Spinner) findViewById(R.id.spinner);

        selecionando();
    }

    public void selecionando () {
        try{
            String sel = "SELECT * FROM curso";
            ResultSet rs = DB.select(sel);
            ArrayList<String> array = new ArrayList<>();
            arrayMateria = new ArrayList<>();

            while(rs.next())
            {
                array.add(rs.getString("nomedocurso"));
                arrayMateria.add(Integer.parseInt(rs.getString("cursoid")));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            meuspinner.setAdapter(adapter);
        }
        catch (Exception e) {
            exibirTexto("Erro", e.getMessage());
        }
    }

    public void salvar(View view) {
        try {
            String pergunta = editTextPergunta.getText().toString();
            String opt1 = editTextOpt1.getText().toString();
            String opt2 = editTextOpt2.getText().toString();
            String optcerta = editTextOptCerta.getText().toString();

            if (!(editTextPergunta.getText().toString().equals("") || editTextPergunta.getText() == null ||
                    editTextOpt1.getText().toString().equals("") || editTextOpt1.getText() == null ||
                    editTextOpt2.getText().toString().equals("") || editTextOpt2.getText() == null ||
                    editTextOptCerta.getText().toString().equals("") || editTextOptCerta.getText() == null

            )) {
                int fk = arrayMateria.get(meuspinner.getSelectedItemPosition());
                DB.update("INSERT INTO quizz (pergunta,opt1,opt2, optcerta, materia_id) VALUES ('" + pergunta + "','" + opt1 + "','" + opt2 + "','" + optcerta + "'," + fk + ")");
                exibirTexto("CRIAÇÃO DE PERGUNTA", "Pergunta de " + meuspinner.getSelectedItem().toString() + " criada com sucesso!");
                editTextPergunta.setText("");
                editTextOpt1.setText("");
                editTextOpt2.setText("");
                editTextOptCerta.setText("");
            }
        }
        catch (Exception e){
            exibirTexto("Erro", e.getMessage());
        }
    }

    public void cancelar (View view){
        finish();
    }
}
