package co.appreactor.proyectofull.modelo.servicios.agenda;

import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import co.appreactor.proyectofull.modelo.entidades.Agenda;

/**
 * Created by Capacitaciones_pc7 on 14/06/2017.
 */

public interface Archivador {

    final String RUTA_ARCHIVO = Environment.getExternalStorageDirectory()
           .getAbsolutePath()+"/agenda";

    void escribir(List<Agenda> listaAgenda) throws IOException;

    List<Agenda> leer() throws IOException;

}
