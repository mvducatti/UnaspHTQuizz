package com.example.marcos.unasphtquizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastrarCurso extends AppCompatActivity {

    private DBCurso dbCurso;
    private EditText editTextNovoCurso;
    public String _mensagem;
    public boolean _status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarcurso);

        editTextNovoCurso = (EditText) findViewById(R.id.editTextNomedoCurso);
    }

    public void exibirTexto(String titulo, String txt){
        AlertDialog alertDialog = new AlertDialog.Builder(CadastrarCurso.this).create();
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

    public void criarcurso (View view){
        ResultSet rs;
        try {
            String criar = editTextNovoCurso.getText().toString();

            if (!(editTextNovoCurso.getText().toString().equals("") || editTextNovoCurso.getText() == null)){
                DB.update("INSERT INTO curso (nomedocurso) VALUES ('" + criar + "')");
                exibirTexto("CRIAÇÃO DE TABELAS", "Tabela " + editTextNovoCurso.getText() + " criada com sucesso");
                editTextNovoCurso.setText("");
            }
        }
        catch (Exception e){
            exibirTexto("Erro", e.getMessage());
        }
    }

    public void cancelarcurso (View view){
        finish();
    }

}
