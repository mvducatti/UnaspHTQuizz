package com.example.marcos.unasphtquizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MenuJogo extends AppCompatActivity {

    private Spinner selecioneamateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menujogo);

        selecioneamateria = (Spinner) findViewById(R.id.spinner2);
    }

    public void exibirTexto(String titulo, String txt){
        AlertDialog alertDialog = new AlertDialog.Builder(MenuJogo.this).create();
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

    public void selecionando () {
        try{
            PreparedStatement pst;
            String sel = "SELECT 'nomedamateria' FROM 'materia' WHERE 'nomedamateria' = ?";
            ResultSet rs = null;
            rs = DB.select(sel);
            ArrayList<String> array = new ArrayList<>();

            while(rs.next())
            {
                array.add(rs.getString("materia"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selecioneamateria.setAdapter(adapter);
        }
        catch (Exception e) {
            exibirTexto("Erro", e.getMessage());
        }
    }

    public void jogar (View view){
        Intent intent =  new Intent(this, Jogando.class);
        startActivity(intent);
    }
}
