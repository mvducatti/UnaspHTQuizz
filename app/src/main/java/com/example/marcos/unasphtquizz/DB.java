package com.example.marcos.unasphtquizz;

import android.app.AlertDialog;
import android.content.DialogInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Italo on 17/10/2017.
 */

public class DB{

    private static Connection conn;
    private static String host = "";
    private static String db = "";
    private static int port = 5432;
    private static String user = "";
    private static String pass = "";
    private static String url = "";

    public DB() {
        super();
    }

    private static Connection conecta() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("org.postgresql.Driver").newInstance();
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
    private static void desconecta(){
        if (conn != null){
            try{
                conn.close();
            }catch (Exception e){
            }finally {
                conn = null;
            }
        }
    }

    public static ResultSet select(String query) throws SQLException, ClassNotFoundException, InterruptedException, ExecutionException, InstantiationException, IllegalAccessException{
        ResultSet resultSet = null;
        conecta();
        resultSet = new ExecuteDB(conn, query).execute().get();
        return resultSet;
    }

    public static ResultSet execute(String query) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        ResultSet resultSet = null;
        conecta();
        resultSet = conecta().prepareStatement(query).executeQuery();;
        return resultSet;
    }
}