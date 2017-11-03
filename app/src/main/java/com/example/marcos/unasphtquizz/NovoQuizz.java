package com.example.marcos.unasphtquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NovoQuizz extends AppCompatActivity {

    private Quizz quizz;
    private EditText editTextPergunta;
    private EditText editTextOpt1;
    private EditText editTextOpt2;
    private EditText editTextOptCerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo);

        this.quizz = new Quizz();
        this.editTextPergunta = (EditText) findViewById(R.id.editTextPergunta);
        this.editTextOpt1 = (EditText) findViewById(R.id.editTextOpt1);
        this.editTextOpt2 = (EditText) findViewById(R.id.editTextOpt1);
        this.editTextOptCerta = (EditText) findViewById(R.id.editTextOptCerta);

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                this.quizz.setId(bundle.getInt("id"));
                this.editTextPergunta.setText(bundle.getString("pergunta"));
                this.editTextOpt1.setText(bundle.getString("opt1"));
                this.editTextOpt2.setText(bundle.getString("opt2"));
                this.editTextOptCerta.setText(bundle.getString("optcerta"));
            }
        }
    }

    public void salvar (View view){
        this.quizz.setPergunta(this.editTextPergunta.getText().toString());
        this.quizz.setOpt1(this.editTextOpt1.getText().toString());
        this.quizz.setOpt2(this.editTextOpt2.getText().toString());
        this.quizz.setOptCerta(this.editTextOptCerta.getText().toString());

        this.quizz.salvar();

        Toast.makeText(this,this.quizz.get_mensagem(), Toast.LENGTH_LONG).show();
        if (quizz.is_status())
            finish();
    }

    public void cancelar (View view){
        finish();
    }
}
