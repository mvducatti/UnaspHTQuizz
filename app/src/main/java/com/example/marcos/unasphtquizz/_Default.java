package com.example.marcos.unasphtquizz;

/**
 * Created by Italo on 17/10/2017.
 */

public class _Default {

    protected String _mensagem;

    public _Default(){
        this._status = true;
        this._mensagem = "";
    }

    public String get_mensagem() {
        return _mensagem;
    }

    public void set_mensagem(String _mensagem) {
        this._mensagem = _mensagem;
    }

    public boolean is_status() {
        return _status;
    }

    public void set_status(boolean _status) {
        this._status = _status;
    }

    protected boolean _status;


}
