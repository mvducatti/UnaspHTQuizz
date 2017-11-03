package com.example.marcos.unasphtquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuario extends AppCompatActivity {

    private Usuario usuario;
    private EditText editTextCDLogin;
    private EditText editTextCDSenha;
    private CheckBox aluno;
    private CheckBox professor;
    private EditText PIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        this.usuario = new Usuario();
        this.editTextCDSenha = (EditText) findViewById(R.id.editTextCDLogin);
        this.editTextCDLogin = (EditText) findViewById(R.id.editTextCDSenha);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                this.editTextCDLogin.setId(bundle.getInt("login"));
                this.editTextCDLogin.setText(bundle.getString("senha"));
            }
        }
    }

    public void salvar(View view) {
        this.usuario.setLogin(this.editTextCDLogin.getText().toString());
        this.usuario.setSenha(this.editTextCDSenha.getText().toString());

        this.usuario.salvar();

        Toast.makeText(this, this.usuario.get_mensagem(), Toast.LENGTH_LONG).show();
        if (usuario.is_status())
            finish();
    }

    public CheckBox getAluno() {
        return aluno;
    }

    public void setAluno(CheckBox aluno) {
        this.aluno = aluno;
    }

    public CheckBox getProfessor() {
        return professor;
    }

    public void setProfessor(CheckBox professor) {
        this.professor = professor;
    }

    public EditText getPIN() {
        return PIN;
    }

    public void setPIN(EditText PIN) {
        this.PIN = PIN;
    }

    public void cdCancelar(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}


