package com.example.marcos.unasphtquizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CadastrarUsuario extends AppCompatActivity {

    private DBUsuario dbUsuario;
    private EditText editTextCDLogin;
    private EditText editTextCDSenha;
    private EditText editTextCDNome;
    private RadioButton aluno;
    private RadioButton professor;
    private EditText PIN;
    private RadioGroup grupo;
    public String _mensagem;
    public boolean _status;

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

        dbUsuario = new Aluno();

        editTextCDNome = (EditText) findViewById(R.id.editTextCDNome);
        editTextCDLogin = (EditText) findViewById(R.id.editTextCDLogin);
        editTextCDSenha = (EditText) findViewById(R.id.editTextCDSenha);
        aluno = (RadioButton) findViewById(R.id.radioButtonAluno);
        professor = (RadioButton) findViewById(R.id.radioButtonProf);
        grupo = (RadioGroup) findViewById(R.id.radioGroup);
        PIN = (EditText) findViewById(R.id.editTextPIN);
        PIN.setText("");

        PIN.setEnabled(false);


        grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                try {
                    if (checkedId == aluno.getId()) {
                        PIN.setEnabled(false);
                        dbUsuario = new Aluno();
                    }
                    if (checkedId == professor.getId()) {
                        PIN.setEnabled(true);
                        dbUsuario = new Professor();
                    }
                }
                catch (Exception e){
                    exibirTexto("Erro", e.getMessage());
                }
            }
        });
    }

    public void salvar(View view) {
        try {
            String nomeusuario = editTextCDNome.getText().toString();
            String loginusuario = editTextCDLogin.getText().toString();
            String senhausuario = editTextCDSenha.getText().toString();

            if (!(editTextCDNome.getText().toString().equals("") || editTextCDNome.getText() == null ||
                    editTextCDLogin.getText().toString().equals("") || editTextCDLogin.getText() == null ||
                    editTextCDSenha.getText().toString().equals("") || editTextCDSenha.getText() == null

            )) {

                dbUsuario.setNome(nomeusuario);
                dbUsuario.setLogin(loginusuario);
                dbUsuario.setSenha(senhausuario);

                dbUsuario.salvar(PIN.getText().toString());

                exibirTexto("CRIAÇÃO DE USUÁRIO", "Usuário " + nomeusuario + " criado com sucesso!");

                editTextCDNome.setText("");
                editTextCDLogin.setText("");
                editTextCDSenha.setText("");
                PIN.setText("");
            }
        }
        catch (Exception e){
            exibirTexto("Erro", e.getMessage());
        }
    }

    public void cdCancelar(View view) {
        finish();
    }
}


