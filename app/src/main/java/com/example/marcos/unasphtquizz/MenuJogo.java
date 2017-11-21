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

    MessageDialog messageDialog;
    private Spinner selecioneamateria;
    ArrayList<Integer> armazenador_materia = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menujogo);

        messageDialog = new MessageDialog(MenuJogo.this);

        selecioneamateria = (Spinner) findViewById(R.id.spinner2);

        selecionando();
    }

    public void selecionando () {
        try{
            String sel = "SELECT * FROM curso";
            ResultSet rs = DB.select(sel);
            ArrayList<String> array = new ArrayList<>();

            while(rs.next())
            {
                array.add(rs.getString("nomedocurso"));
                armazenador_materia.add(Integer.parseInt(rs.getString("cursoid")));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selecioneamateria.setAdapter(adapter);
        }
        catch (Exception e) {
            messageDialog.showText("Erro", e.getMessage());
        }
    }

    public void jogar (View view){
        int pk = armazenador_materia.get(selecioneamateria.getSelectedItemPosition());
        Intent intent =  new Intent(this, new Jogando(pk).getClass());
        startActivity(intent);
    }
}
