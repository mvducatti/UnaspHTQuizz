package com.example.marcos.unasphtquizz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by Italo on 17/10/2017.
 */

public class DB extends _Default implements Runnable {

    private Connection conn;
    private String host = "";
    private String db = "";
    private int port = 5432;
    private String user = "";
    private String pass = "";
    private String url = "jdbc:postgresql:";


    public DB() {
        super();
        this.url = String.format(this.url, this.host, this.port, this.db);

        this.conecta();
        this.desconecta();
    }

    @Override
    public void run() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (Exception e) {
            this._mensagem = e.getMessage();
            this._status = false;
        }
    }

    private void conecta() {
        Thread thread = new Thread(this);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            this._mensagem = e.getMessage();
            this._status = false;
        }
    }
    private void desconecta(){
        if (this.conn != null){
            try{
                this.conn.close();
            }catch (Exception e){

            }finally {
                this.conn = null;
            }
        }
    }
    public ResultSet select(String query){
        this.conecta();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.conn, query).execute().get();
        }catch (Exception e){
        this._status = false;
            this._mensagem = e.getMessage();
        }
        return resultSet;
    }
    public ResultSet execute(String query){
        this.conecta();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.conn, query).execute().get();
        }catch (Exception e){
            this._status = false;
            this._mensagem = e.getMessage();
        }
        return resultSet;
    }
}
