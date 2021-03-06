package com.example.marcos.unasphtquizz;

import android.app.AlertDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Italo on 03/11/2017.
 */

public abstract class DBUsuario{

    private int id;
    private String login;
    private String senha;
    private String nome;
    private int tipoUsuario;

    public String _mensagem;
    public boolean _status;

    public DBUsuario(int tipoUsusario){
        super();
        this.id = -1;
        this.nome = "";
        this.login = "";
        this.senha = "";
        this.tipoUsuario = tipoUsusario;
    }

    public ArrayList<DBUsuario> getLista(){
        DB db = new DB();
        ArrayList<DBUsuario> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM usuario");
            if (resultSet != null){
                while (resultSet.next()){

                    DBUsuario obj = null;

                    if (tipoUsuario == 1) {
                        obj = new Professor();
                    }
                    if (tipoUsuario == 2) {
                        obj = new Aluno();
                    }

                    obj.setId(resultSet.getInt("id"));
                    obj.setNome(resultSet.getString("nome"));
                    obj.setLogin(resultSet.getString("login"));
                    obj.setSenha(resultSet.getString("senha"));
                    obj.setTipoUsusario(Integer.parseInt(resultSet.getString("tipousuario")));
                    lista.add(obj);

                    obj = null;
                }
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return lista;
    }

    public void salvar(String teste) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, LoginException {
        String comando = "";
        if (this.getId() == -1) {
            comando = String.format("INSERT INTO usuario (nome,login,senha,tipousuario) VALUES ('%s','%s','%s',%d);",
                    this.getNome(), this.getLogin(), this.getSenha(), this.tipoUsuario);
        } else {
            comando = String.format("UPDATE usuario SET nome ='%s', login ='%s', senha = '%s', tipo = '%d' WHERE id = %d;",
                    this.getNome(), this.getLogin(), this.getSenha(), this.tipoUsuario, this.getId());
        }
        DB db = new DB();
        db.update(comando);
    }

    public void apagar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String comando = String.format("DELETE FROM usuario WHERE id = %d;", this.getId());
        DB db =  new DB();
        db.execute(comando);
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

    public int getTipoUsusario() {
        return tipoUsuario;
    }

    public void setTipoUsusario(int tipoUsusario) {
        this.tipoUsuario = tipoUsusario;
    }
}

