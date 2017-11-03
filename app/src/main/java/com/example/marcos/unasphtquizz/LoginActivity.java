package com.example.marcos.unasphtquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void login(View view){
        Intent intent =  new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void criarUsuario(View view){
        Intent intent =  new Intent(this, CadastroUsuario.class);
        startActivity(intent);
    }
}
