package com.example.marcos.unasphtquizz;

import java.sql.SQLException;

/**
 * Created by Italo on 05/11/2017.
 */

public class Professor extends DBUsuario {

    public Professor() {
        super(1);
    }

    @Override
    public void salvar(String pin) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        if (pin.equals("3405")){
            super.salvar(pin);
        }
    }
}