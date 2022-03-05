package com.example.cadastroalunos.util;

import android.view.View;
import android.widget.TextView;
import com.example.cadastroalunos.R;
import com.google.android.material.snackbar.Snackbar;

public class Util {

    /***
     *
     * @param view: Onde será exibido
     * @param msg: Mensagem
     * @param tipo: tipo do icone 0-Erro, 1-Sucesso, 2-Atenção
     */
    public static void customSnakeBar(View view, String msg, int tipo) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View viewSnake = snackbar.getView();

        TextView tv = (TextView) viewSnake.findViewById(R.id.snakebar_text);
/*
        if (tipo == 0)
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_cancel, 0, 0, 0);
        else if (tipo == 1)
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_confirm, 0, 0, 0);
        else if (tipo == 2)
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_caution, 0, 0, 0);
*/
        snackbar.show();

    }

    public static String padLeftZeros(String str, int n) {
        return String.format("%1$" + n + "s", str).replace(' ', '0');
    }

}
