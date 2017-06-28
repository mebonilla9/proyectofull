package co.appreactor.proyectofull.negocio.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Capacitaciones_pc7 on 27/06/2017.
 */

public final class PreferenciasUtil {

    public static void guardarPreferencias(String llave, String valor, Context contexto){
        SharedPreferences preferencias = contexto.getSharedPreferences("proyectoFull",Context.MODE_PRIVATE);
        SharedPreferences.Editor editorPreferencias = preferencias.edit();
        editorPreferencias.putString(llave,valor);
    }

    public static String consultarPreferencia(String llave, Context contexto){
        SharedPreferences preferencias = contexto.getSharedPreferences("proyectoFull",Context.MODE_PRIVATE);
        return preferencias.getString(llave,"");
    }

}
