package com.example.marcos.unasphtquizz;

/**
 * Created by Italo on 05/11/2017.
 */

public class Professor extends Usuario{

    public Professor() {
        super(1);
    }

    @Override
    public void salvar(String pin){
        if (pin.equals("3405")){
            super.salvar(pin);
        }
    }
}