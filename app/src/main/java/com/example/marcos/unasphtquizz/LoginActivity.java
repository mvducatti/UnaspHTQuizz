package com.example.marcos.unasphtquizz;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private EditText editLogin, editSenha;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editLogin = (EditText) findViewById(R.id.textView);
        editSenha = (EditText) findViewById(R.id.editText2);
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
        Connection con = null;
        PreparedStatement pst;
        String sel = "SELECT login, senha, tipousuario FROM usuario WHERE login == '?' AND senha == '?'";
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:postgresql:marcosducatti.postgresql.dbaas.com.br", "marcosducatti", "ducatti2017");
            pst = con.prepareStatement(sel);
            pst.setString(1, String.valueOf(editLogin.getText()));
            pst.setString(2, String.valueOf(editSenha.getText()));
            rs = pst.executeQuery();

            while (rs.next()){
                String login = rs.getString("login");
                String senha = rs.getString("senha");;
                int tipo = Integer.parseInt(rs.getString("login"));

                if (login.equals(editLogin.getText().toString())){
                    if (senha.equals(editSenha.getText().toString())){
                        Intent intent = null;
                        switch (tipo){
                            case 1:
                                intent =  new Intent(this, Jogando.class);
                                break;
                            case 2:
                                intent =  new Intent(this, CadastrarQuizz.class);
                                break;
                            case 3:
                                intent =  new Intent(this, CadastrarCurso.class);
                                break;
                        }
                        startActivity(intent);
                    }
                }
            }
        } catch (SQLException e) {
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
