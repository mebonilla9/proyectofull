package co.appreactor.proyectofull.negocio.util;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.negocio.actividades.MainActivity;

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
        alerta.show();
    }

    public static void mostrarNotificacion(Context contexto, String titulo, String mensaje) {
        Intent intent = new Intent(contexto, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(contexto, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(contexto);
        builder.setContentTitle(titulo);
        builder.setContentText(mensaje);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) contexto.getSystemService(
                Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
