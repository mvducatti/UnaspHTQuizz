package com.example.marcos.unasphtquizz;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Italo on 03/11/2017.
 */

public class Usuario extends _Default{

        private int id;
        private String login;
        private String senha;

        public Usuario(){
            super();
            this.id = -1;
            this.login = "";
            this.senha = "";
        }

        public ArrayList<Quizz> getLista(){
            DB db = new DB();
            ArrayList<Quizz> lista = new ArrayList<>();
            try {
                ResultSet resultSet = db.select("SELECT * FROM usuario");
                if (resultSet != null){
                    while (resultSet.next()){
                        Quizz obj = new Quizz();
                        obj.setId(resultSet.getInt("login"));
                        obj.setPergunta(resultSet.getString("senha"));

                        lista.add(obj);
                        obj = null;
                    }
                }
            }catch (Exception ex){
                this._mensagem = ex.getMessage();
                this._status = false;
                this._status = false;
            }
            return lista;
        }

        public void salvar(){
            String comando = "";
            if (this.getId() == -1){
                comando = String.format("INSERT INTO usuarios (login, senha) VALUES ('%s','%s');",
                        this.getLogin(),this.getSenha());
            }else {
                comando = String.format("UPDATE usuarios SET login = '%s', senha = '%s' WHERE id = %d;",
                        this.getLogin(),this.getSenha());
            }
            DB db =  new DB();
            db.execute(comando);
            this._mensagem = db._mensagem;
            this._status = db._status;
        }

        public void apagar(){
            String comando = String.format("DELETE FROM usuario WHERE id = %d;", this.getId());

            DB db =  new DB();
            db.execute(comando);
            this._mensagem = db._mensagem;
            this._status = db._status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }


    }

