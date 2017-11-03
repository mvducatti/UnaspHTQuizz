package com.example.marcos.unasphtquizz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abreNovo(View view){
        Intent intent =  new Intent(this, NovoQuizz.class);
        startActivity(intent);
    }

    public void abreLista(View view){
        Intent intent =  new Intent(this, ListaActivity.class);
        startActivity(intent);
    }

}
