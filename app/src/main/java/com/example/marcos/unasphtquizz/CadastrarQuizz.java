package com.example.marcos.unasphtquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarQuizz extends AppCompatActivity {

    private DBQuizz DBQuizz;
    private EditText editTextPergunta;
    private EditText editTextOpt1;
    private EditText editTextOpt2;
    private EditText editTextOptCerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarquizz);

        this.DBQuizz = new DBQuizz();
        this.editTextPergunta = (EditText) findViewById(R.id.editTextPergunta);
        this.editTextOpt1 = (EditText) findViewById(R.id.editTextOpt1);
        this.editTextOpt2 = (EditText) findViewById(R.id.editTextOpt1);
        this.editTextOptCerta = (EditText) findViewById(R.id.editTextOptCerta);

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                this.DBQuizz.setId(bundle.getInt("id"));
                this.editTextPergunta.setText(bundle.getString("pergunta"));
                this.editTextOpt1.setText(bundle.getString("opt1"));
                this.editTextOpt2.setText(bundle.getString("opt2"));
                this.editTextOptCerta.setText(bundle.getString("optcerta"));
            }
        }
    }

    public void salvar (View view){
        this.DBQuizz.setPergunta(this.editTextPergunta.getText().toString());
        this.DBQuizz.setOpt1(this.editTextOpt1.getText().toString());
        this.DBQuizz.setOpt2(this.editTextOpt2.getText().toString());
        this.DBQuizz.setOptCerta(this.editTextOptCerta.getText().toString());

        this.DBQuizz.salvar();

        Toast.makeText(this,this.DBQuizz.get_mensagem(), Toast.LENGTH_LONG).show();
        if (DBQuizz.is_status())
            finish();
    }

    public void cancelar (View view){
        finish();
    }
}
