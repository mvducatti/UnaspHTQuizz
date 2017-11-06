package com.example.marcos.unasphtquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MenuJogo extends AppCompatActivity {

    private Spinner selecioneamateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menujogo);

        selecioneamateria = (Spinner) findViewById(R.id.spinner2);
    }

    public void jogar (View view){
        Intent intent =  new Intent(this, Jogando.class);
        startActivity(intent);
    }
}
