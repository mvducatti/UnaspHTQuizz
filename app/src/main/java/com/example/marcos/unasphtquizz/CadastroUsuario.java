package com.example.marcos.unasphtquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastroUsuario extends AppCompatActivity {

    private Usuario usuario;
    private EditText editTextCDLogin;
    private EditText editTextCDSenha;
    private EditText editTextCDNome;
    private RadioButton aluno;
    private RadioButton professor;
    private EditText PIN;
    private RadioGroup grupo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

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
                    usuario = new Aluno();
                }
                if(checkedId==R.id.radioButtonProf)
                {
                    PIN.setEnabled(true);
                    usuario = new Professor();
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

        this.usuario.setNome(this.editTextCDNome.getText().toString());
        this.usuario.setLogin(this.editTextCDLogin.getText().toString());
        this.usuario.setSenha(this.editTextCDSenha.getText().toString());

        this.usuario.salvar(PIN.getText().toString());

        Toast.makeText(this,this.usuario.get_mensagem(), Toast.LENGTH_LONG).show();
        if (usuario.is_status())
            finish();
    }

    public void cdCancelar(View view) {
            finish();
        }
    }


