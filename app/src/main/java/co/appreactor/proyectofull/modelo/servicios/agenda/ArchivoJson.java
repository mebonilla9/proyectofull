package co.appreactor.proyectofull.modelo.servicios.agenda;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.appreactor.proyectofull.modelo.entidades.Agenda;

/**
 * Created by Capacitaciones_pc7 on 14/06/2017.
 */

public class ArchivoJson implements Archivador {

    private final String NOMBRE_ARCHIVO = "/agenda_json.txt";

    public ArchivoJson(Context contexto) {
        try {
            File carpetaAgenda = new File(RUTA_ARCHIVO);
            if (!carpetaAgenda.exists()) {
                carpetaAgenda.mkdir();
            }
            Toast.makeText(contexto, "Carpeta de agenda existe!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(contexto, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void escribir(List<Agenda> listaAgenda) throws IOException {
        String json = new Gson().toJson(listaAgenda);
        PrintStream escritor = new PrintStream(RUTA_ARCHIVO + File.separator + NOMBRE_ARCHIVO);
        escritor.print(json);
        escritor.flush();
        escritor.close();
    }

    @Override
    public List<Agenda> leer() throws IOException {
        List<Agenda> listaAgenda = new ArrayList<>();
        /**
         * Objetos del canal de lectura
         */
        // noob
        /*FileInputStream fis = new FileInputStream(RUTA_ARCHIVO + File.separator + NOMBRE_ARCHIVO);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);*/

        // pro
        BufferedReader lector = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                RUTA_ARCHIVO + File.separator + NOMBRE_ARCHIVO
                        )
                )
        );

        String contenido = "";
        while (lector.ready()){
            contenido += lector.readLine();
        }
        lector.close();
        /**
         * usar reflexion para convertir un String en formato JSON a una estructura de objetos de
         * clase en java, en tiempo de ejecución
         */
        Type tipoDato = new TypeToken<List<Agenda>>(){}.getType();
        listaAgenda = new Gson().fromJson(contenido,tipoDato);
        return listaAgenda;
    }
}
