package com.example.marcos.unasphtquizz;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Jogando extends AppCompatActivity {

    private static int pkdamateria;
    private MessageDialog messageDialog;
    private TextView pergunta;
    private Button []opcao = new Button[3];
    private Random random = new Random();
    private int contador, certas;

    public Jogando(){}

    public Jogando(int pkdamateria){
        this.pkdamateria = pkdamateria;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogando);

        messageDialog = new MessageDialog(Jogando.this);

        pergunta = findViewById(R.id.textViewPergunta);
        opcao[0] = findViewById(R.id.btnOpt1);
        opcao[1] = findViewById(R.id.btnOpt2);
        opcao[2] = findViewById(R.id.btnOpCerta);
        try {
            final ResultSet rs = DB.select("SELECT * FROM quizz WHERE materia_id = " + pkdamateria);
            set(rs);
        }catch (Exception e){
            messageDialog.showText("Deu ruim", "muito ruim...\n" + e.getMessage());
        }
    }

    protected void set(final ResultSet rs){
        try {
            if(rs.next()) {

                contador++;

                for (int i = 0; i < opcao.length; i++) {
                    Button guardar = opcao[i];
                    int j = random.nextInt(opcao.length);

                    opcao[i] = opcao[j];
                    opcao[j] = guardar;
                }
                pergunta.setText(rs.getString("pergunta"));
                opcao[0].setText(rs.getString("opt1"));
                opcao[1].setText(rs.getString("opt2"));
                opcao[2].setText(rs.getString("optcerta"));

                View.OnClickListener metodo = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        set(rs);
                    }
                };

                opcao[0].setOnClickListener(metodo);
                opcao[1].setOnClickListener(metodo);

                opcao[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        certas++;
                        set(rs);
                    }
                });
            }
            else{
                messageDialog.setButtonMeth(new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                messageDialog.showText("FIM DO QUIZZ", "Você completou o jogo mais inútil que existe. Você acertou " + certas +  " de " + contador + " perguntas");
                messageDialog.useDefaltMethod();
            }
        } catch (SQLException e) {
            messageDialog.showText("Deu ruim", "muito ruim...\n" + e.getMessage());
        }
    }
}
