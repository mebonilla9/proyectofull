package co.appreactor.proyectofull.negocio.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.negocio.fragmentos.PokedexFragment;
import co.appreactor.proyectofull.negocio.fragmentos.WebViewFragment;

/**
 * Created by Capacitaciones_pc7 on 27/06/2017.
 */

public final class FragmentoUtil {

    public static void obtenerFragmentoSeleccionado(int idVista, Bundle argumentos,
                                                    FragmentTransaction transaccion,
                                                    int idContenedor) {
        Fragment fragmento = null;
        String tag = "";
        switch (idVista) {
            case R.id.nav_navegador:
                fragmento = new WebViewFragment();
                tag = "Navegador";
                break;
            case R.id.nav_pokedex:
                fragmento = new PokedexFragment();
                tag = "Pokedex";
                break;
            default:
                fragmento = new Fragment();
                break;
        }
        if (argumentos != null){
            fragmento.setArguments(argumentos);
        }
        transaccion.replace(idContenedor,fragmento,tag).addToBackStack(tag).commit();
    }

}
