package com.example.marcos.unasphtquizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CadastrarUsuario extends AppCompatActivity {

    private DBUsuario DBUsuario;
    private EditText editTextCDLogin;
    private EditText editTextCDSenha;
    private EditText editTextCDNome;
    private RadioButton aluno;
    private RadioButton professor;
    private EditText PIN;
    private RadioGroup grupo;

    public void exibirTexto(String titulo, String txt){
        AlertDialog alertDialog = new AlertDialog.Builder(CadastrarUsuario.this).create();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarusuario);

        this.editTextCDNome = (EditText) findViewById(R.id.editTextPergunta);
        this.editTextCDLogin = (EditText) findViewById(R.id.editTextOpt1);
        this.editTextCDSenha = (EditText) findViewById(R.id.editTextOpt1);
        aluno = (RadioButton) findViewById(R.id.radioButtonAluno);
        professor = (RadioButton) findViewById(R.id.radioButtonProf);
        grupo = (RadioGroup) findViewById(R.id.radioGroup);
        PIN = (EditText) findViewById(R.id.editTextPIN);

        PIN.setEnabled(false);

        grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.radioButtonAluno)
                {
                    PIN.setEnabled(false);
                    DBUsuario = new Aluno();
                }
                if(checkedId==R.id.radioButtonProf)
                {
                    PIN.setEnabled(true);
                    DBUsuario = new Professor();
                }
            }
    });

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                this.editTextCDNome.setText(bundle.getString("nome"));
                this.editTextCDLogin.setText(bundle.getString("login"));
                this.editTextCDSenha.setText(bundle.getString("senha"));
            }
        }
    }

    public void salvar(View view) {
        try {
            this.DBUsuario.setNome(this.editTextCDNome.getText().toString());
            this.DBUsuario.setLogin(this.editTextCDLogin.getText().toString());
            this.DBUsuario.setSenha(this.editTextCDSenha.getText().toString());

            this.DBUsuario.salvar(PIN.getText().toString());

            Toast.makeText(this, this.DBUsuario.get_mensagem(), Toast.LENGTH_LONG).show();
            if (DBUsuario.is_status())
                finish();
        }
        catch (Exception e){
            exibirTexto("Erro", e.getMessage());
        }
    }

    public void cdCancelar(View view) {
            finish();
        }
    }


