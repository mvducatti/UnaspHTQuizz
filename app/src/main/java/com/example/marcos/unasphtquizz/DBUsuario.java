package com.example.marcos.unasphtquizz;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Italo on 03/11/2017.
 */

public abstract class DBUsuario extends _Default{

        private int id;
        private String login;
        private String senha;
        private String nome;
        private int tipoUsusario;

        public DBUsuario(int tipoUsusario){
            super();
            this.id = -1;
            this.nome = "";
            this.login = "";
            this.senha = "";
            this.tipoUsusario = tipoUsusario;
        }

    public ArrayList<DBUsuario> getLista(){
        DB db = new DB();
        ArrayList<DBUsuario> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM usuario");
            if (resultSet != null){
                while (resultSet.next()){
                    DBUsuario obj = null;

                    if (tipoUsusario == 1) {
                        obj = new Professor();
                    }
                    if (tipoUsusario == 2) {
                        obj = new Aluno();
                    }

                    obj.setId(resultSet.getInt("id"));
                    obj.setNome(resultSet.getString("nome"));
                    obj.setLogin(resultSet.getString("login"));
                    obj.setSenha(resultSet.getString("senha"));

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

        public void salvar(String teste){
            String comando = "";
            if (this.getId() == -1){
                comando = String.format("INSERT INTO usuarios (nome,login,senha,tipo) VALUES ('%s','%s','%s','%d');",
                        this.getNome(),this.getLogin(),this.getSenha(),this.tipoUsusario);
            }else {
                comando = String.format("UPDATE usuarios SET nome ='%s', login ='%s', senha = '%s', tipo = '%d' WHERE id = %d;",
                        this.getNome(), this.getLogin(),this.getSenha(),this.tipoUsusario);
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

        public String getNome() {
        return nome;
    }

        public void setNome(String nome) {
        this.nome = nome;
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

