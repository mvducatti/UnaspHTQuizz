package com.example.marcos.unasphtquizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ListaActivity extends AppCompatActivity {

    private ListView listViewUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_lista);

        this.listViewUsuario = (ListView) findViewById(R.id.listviewUsuario);
        this.listViewUsuario.setAdapter(new UsuarioAdapter(this, new DBQuizz().getLista()));
    }
}


