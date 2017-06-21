package co.appreactor.proyectofull.negocio.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import co.appreactor.proyectofull.R;

/**
 * Created by Capacitaciones_pc7 on 14/06/2017.
 */

public final class AlertaUtil {

    public static void mostrarAlerta(
            String titulo, String mensaje,
            DialogInterface.OnClickListener aceptar,
            DialogInterface.OnClickListener cancelar,
            Context contexto){

        AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
        alerta.setTitle(titulo);
        alerta.setMessage(mensaje);
        alerta.setPositiveButton(R.string.aceptar_btn,aceptar);
        alerta.setNegativeButton(R.string.cancelar_btn,cancelar);
    }

}
