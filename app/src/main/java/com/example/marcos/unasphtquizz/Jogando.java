package com.example.marcos.unasphtquizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Jogando extends AppCompatActivity {

    private TextView pergunta;
    private Button primeiraopcao;
    private Button segundaopcao;
    private Button respostacorreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogando);

        pergunta = (TextView) findViewById(R.id.textViewPergunta);
        primeiraopcao = (Button) findViewById(R.id.btnOpt1);
        segundaopcao = (Button) findViewById(R.id.btnOpt2);
        respostacorreta = (Button) findViewById(R.id.btnOpCerta);
    }
}
