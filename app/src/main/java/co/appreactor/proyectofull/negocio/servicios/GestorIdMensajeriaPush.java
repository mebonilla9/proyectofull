package co.appreactor.proyectofull.negocio.servicios;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.negocio.util.PreferenciasUtil;

/**
 * Created by Capacitaciones_pc7 on 07/07/2017.
 */

public class GestorIdMensajeriaPush extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        // guardar token en preferencias compartidas
        PreferenciasUtil.guardarPreferencias(getString(R.string.fcm_token),token,getApplicationContext());
        // Suscribir a un tema de notificacion o "Topic"
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.tema_notificacion));
    }
}
