package com.example.marcos.unasphtquizz;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Italo on 06/11/2017.
 */

public class DBCurso extends _Default{

    private int id;
    private String nomedocurso;

    public DBCurso(int novoCurso){
        super();
        this.id = -1;
        this.nomedocurso = "";
    }

    public ArrayList<DBCurso> getLista(){
        DB db = new DB();
        ArrayList<DBCurso> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM curso");
            if (resultSet != null){
                while (resultSet.next()){
                    DBCurso obj = null;

                    obj.setId(resultSet.getInt("id"));
                    obj.setNomedocurso(resultSet.getString("nome do curso"));

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
            comando = String.format("INSERT INTO usuarios (nome,login,senha,tipo) VALUES ('%s');",
                    this.getNomedocurso());
        }else {
            comando = String.format("UPDATE usuarios SET curso ='%s'WHERE id = %d;",
                    this.getNomedocurso());
        }
        DB db =  new DB();
        db.execute(comando);
        this._mensagem = db._mensagem;
        this._status = db._status;
    }

    public void apagar(){
        String comando = String.format("DELETE FROM curso WHERE id = %d;", this.getId());

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

    public String getNomedocurso() {
        return nomedocurso;
    }

    public void setNomedocurso(String nomedocurso) {
        this.nomedocurso = nomedocurso;
    }
}

