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

import java.sql.SQLException;

public class CadastrarCurso extends AppCompatActivity {

    private DBCurso DBCurso;
    private EditText edtTextNovoCurso;
    private Button  btnCriarCurso, btnCancelarCurso, btnVisualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarcurso);
        edtTextNovoCurso = (EditText) findViewById(R.id.editTextNomedoCurso);

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                this.DBCurso.setId(bundle.getInt("id"));
                this.edtTextNovoCurso.setText(bundle.getString("novo curso"));
            }
        }
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
        try {
            this.DBCurso.setNomedocurso(this.edtTextNovoCurso.getText().toString());

            this.DBCurso.salvar();

            Toast.makeText(this, this.DBCurso.get_mensagem(), Toast.LENGTH_LONG).show();
            if (DBCurso.is_status())
                finish();
        }
        catch (Exception e){
            exibirTexto("Erro", e.getMessage());
        }
    }

    public void cancelarcurso (View view){
    finish();
    }

    public void visualizarcurso (View view){
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        catch (Exception e){
            exibirTexto("Erro", e.getMessage());
        }
    }
}
