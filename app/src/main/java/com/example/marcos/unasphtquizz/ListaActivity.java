package com.example.marcos.unasphtquizz;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private ListView listViewUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        this.listViewUsuario = (ListView) findViewById(R.id.listviewUsuario);
        this.listViewUsuario.setAdapter(new UsuarioAdapter(this, new Usuario().getLista()));
    }
}


