package com.example.marcos.unasphtquizz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Italo on 06/11/2017.
 */

public class DBCurso{

    private int id;
    private String nomedocurso;

    public String _mensagem;
    public boolean _status;

    public DBCurso(){
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

                    DBCurso obj = new DBCurso();

                    obj.setId(resultSet.getInt("id"));
                    obj.setNomedocurso(resultSet.getString("nomedocurso"));
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

    public void salvar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String comando = "";
        if (this.getId() == -1){
            comando = String.format("INSERT INTO curso (nomedocurso) VALUES ('%s');",
                    this.getNomedocurso());
        }else {
            comando = String.format("UPDATE usuarios SET curso ='%s'WHERE id = %d;",
                    this.getNomedocurso());
        }
        DB db =  new DB();
        db.execute(comando);
    }

    public void apagar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String comando = String.format("DELETE FROM curso WHERE id = %d;", this.getId());

        DB db =  new DB();
        db.execute(comando);
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