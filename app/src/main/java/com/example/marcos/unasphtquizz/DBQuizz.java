package com.example.marcos.unasphtquizz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Italo on 17/10/2017.
 */

public class DBQuizz extends _Default {

    private int id;
    private String pergunta;
    private String opt1;
    private String opt2;
    private String optCerta;

    public DBQuizz(){
        super();
        this.id = -1;
        this.opt1 = "";
        this.pergunta = "";
        this.opt2  = "";
        this.optCerta = "";
    }

    public ArrayList<DBQuizz> getLista(){
        DB db = new DB();
        ArrayList<DBQuizz> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM quizz");
            if (resultSet != null){
                while (resultSet.next()){
                    DBQuizz obj = new DBQuizz();
                    obj.setId(resultSet.getInt("id"));
                    obj.setPergunta(resultSet.getString("pergunta"));
                    obj.setOpt1(resultSet.getString("opt1"));
                    obj.setOpt2(resultSet.getString("opt2"));
                    obj.setOptCerta(resultSet.getString("optcerta"));

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
            comando = String.format("INSERT INTO quiz (pergunta, opt1, opt2, optcerta) VALUES ('%s','%s','%s','%s');",
                    this.getPergunta(),this.getOpt1(),this.getOpt2(),this.getOptCerta());
        }else {
            comando = String.format("UPDATE quiz SET pergunta = '%s', opt1 = '%s', opt2 = '%s', optcerto = '%s' WHERE id = %d;",
                    this.getPergunta(),this.getOpt1(),this.getOpt2(),this.getOptCerta(),this.getId());
        }
        DB db =  new DB();
        db.execute(comando);
    }

    public void apagar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String comando = String.format("DELETE FROM quiz WHERE id = %d;", this.getId());
        DB db =  new DB();
        db.execute(comando);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String telefone) {
        this.opt2 = telefone;
    }

    public String getOptCerta() {
        return optCerta;
    }

    public void setOptCerta(String optCerta) {
        this.opt2 = optCerta;
    }
}
