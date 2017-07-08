package co.appreactor.proyectofull.negocio.servicios;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.negocio.util.AlertaUtil;

/**
 * Created by Capacitaciones_pc7 on 07/07/2017.
 */

public class ServicioMensajeriaPushFcm extends FirebaseMessagingService {

    private static final String TAG = "Notificacion";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getFrom().equals("/topics/"+getString(R.string.tema_notificacion))){
            String titulo = remoteMessage.getNotification().getTitle();
            String mensaje = remoteMessage.getNotification().getBody();
            AlertaUtil.mostrarNotificacion(getApplicationContext(), titulo, mensaje);
        }
    }
}
