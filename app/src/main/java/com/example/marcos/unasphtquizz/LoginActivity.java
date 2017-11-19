package com.example.marcos.unasphtquizz;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.ResultSet;

public class LoginActivity extends AppCompatActivity {

    private EditText editLogin, editSenha;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editLogin = (EditText) findViewById(R.id.loginText);
        editSenha = (EditText) findViewById(R.id.SenhaText);
    }

    public void exibirTexto(String titulo, String txt){
        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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

    public void login(View view){
        ResultSet rs;

        try {
            String testelogin = editLogin.getText().toString();
            String testesenha = editSenha.getText().toString();
            rs = DB.execute("SELECT login, senha, tipousuario FROM usuario WHERE login = '" + testelogin + "' AND senha = '" + testesenha + "'");
            while (rs.next()){
                String login = rs.getString("login");
                String senha = rs.getString("senha");;
                int tipo = Integer.parseInt(rs.getString("tipousuario"));
                if (login.equals(testelogin)){
                    if (senha.equals(testesenha)){
                        Intent intent = null;
                        switch (tipo){
                            case 1:
                                intent =  new Intent(this, MenuJogo.class);
                                break;
                            case 2:
                                intent =  new Intent(this, CadastrarQuizz.class);
                                break;
                            case 3:
                                intent =  new Intent(this, CadastrarCurso.class);
                                break;
                        }
                        startActivity(intent);
                        return;
                    }
                }
            }
            throw new LoginException("Usuário ou senha incorretos");
        }catch (LoginException e){
            exibirTexto("Erro de Login", e.getMessage());
        }
        catch (Exception e) {
            exibirTexto("Erro", e.getMessage());
        }
    }


    public void criarUsuario(View view){
        try {
            Intent intent = new Intent(this, CadastrarUsuario.class);
            startActivity(intent);
        }
        catch (Exception e){
            exibirTexto("Erro", e.getMessage());
        }
    }
}
