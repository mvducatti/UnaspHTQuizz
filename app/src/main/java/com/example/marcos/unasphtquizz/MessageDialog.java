package com.example.marcos.unasphtquizz;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Italo on 19/11/2017.
 */

public class MessageDialog {

    private Context context;
    private DialogInterface.OnClickListener defaultMeth = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };
    private DialogInterface.OnClickListener buttonMeth;

    public MessageDialog(Context context){
        this.context = context;
        useDefaltMethod();
    }

    public void setButtonMeth(DialogInterface.OnClickListener buttonMeth){
        this.buttonMeth = buttonMeth;
    }

    public void useDefaltMethod(){
        buttonMeth = defaultMeth;
    }

    public void showText(String titulo, String txt){
        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(context).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK", buttonMeth);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}