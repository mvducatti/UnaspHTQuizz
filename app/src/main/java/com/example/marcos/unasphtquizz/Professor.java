package com.example.marcos.unasphtquizz;

import java.sql.SQLException;

/**
 * Created by Italo on 05/11/2017.
 */

public class Professor extends DBUsuario {

    public Professor() {
        super(2);
    }

    @Override
    public void salvar(String pin) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, LoginException {
        if (pin.equals("3405")){
            super.salvar(pin);
        }
        else{
            throw new  LoginException("PIN incorreto");
        }
    }
}