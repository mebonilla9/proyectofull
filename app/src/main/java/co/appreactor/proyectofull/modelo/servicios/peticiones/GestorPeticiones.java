package co.appreactor.proyectofull.modelo.servicios.peticiones;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Capacitaciones_pc7 on 14/06/2017.
 */

public final class GestorPeticiones {

    private final static String URL_SERVIDOR = "http://appreactor.co/proyectofull/usuario/";

    public static void enviarPeticion(final String servicio, final String data, final Handler handler) {
        Thread hiloPeticion = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL ruta = new URL(URL_SERVIDOR + servicio);
                    HttpURLConnection conexion = (HttpURLConnection) ruta.openConnection();
                    conexion.setDoInput(true);
                    conexion.setDoOutput(true);
                    conexion.setRequestMethod("POST");

                    // buffer de escritura
                    OutputStreamWriter salida = new OutputStreamWriter(
                            conexion.getOutputStream()
                    );
                    salida.write(data);
                    salida.flush();
                    salida.close();

                    // Procesar respuesta del servidor buffer de lectura
                    BufferedReader entrada = new BufferedReader(
                            new InputStreamReader(
                                    conexion.getInputStream()
                            )
                    );

                    String linea = entrada.readLine();
                    String respuesta = "";
                    while (linea != null){
                        respuesta += linea;
                        linea = entrada.readLine();
                    }

                    entrada.close();
                    conexion.disconnect();

                    // Crear el mensaje con el contenido de la respuesta del servidor
                    Message mensaje = new Message();
                    mensaje.what = 1;
                    mensaje.obj = respuesta;

                    handler.sendMessage(mensaje);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        hiloPeticion.start();
    }

}
