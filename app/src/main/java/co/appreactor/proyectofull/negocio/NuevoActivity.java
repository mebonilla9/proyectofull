package co.appreactor.proyectofull.negocio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.modelo.entidades.Agenda;
import co.appreactor.proyectofull.modelo.servicios.agenda.Archivador;
import co.appreactor.proyectofull.modelo.servicios.agenda.ArchivoJson;
import co.appreactor.proyectofull.negocio.util.AlertaUtil;

public class NuevoActivity extends AppCompatActivity {

    private TextInputLayout txtNombre;
    private TextInputLayout txtTelefon;
    private TextInputLayout txtCorreo;
    private Button btnGuardar;

    private Archivador archivo;
    private DialogInterface.OnClickListener aceptarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        this.btnGuardar = (Button) findViewById(R.id.btnGuardar);
        this.txtCorreo = (TextInputLayout) findViewById(R.id.txtCorreo);
        this.txtTelefon = (TextInputLayout) findViewById(R.id.txtTelefon);
        this.txtNombre = (TextInputLayout) findViewById(R.id.txtNombre);
        archivo = new ArchivoJson(NuevoActivity.this);
        asignarEventos();
    }

    private void asignarEventos() {
        aceptarDialog = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(NuevoActivity.this,ListaActivity.class));
            }
        };

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Agenda> listaAgenda = new ArrayList<>();
                try{
                    try{
                        listaAgenda = archivo.leer();
                    } catch(IOException e){
                        Log.e("btnGuardar-click",e.getMessage());
                    }
                    Agenda nuevoContacto = new Agenda();
                    nuevoContacto.setNombre(txtNombre.getEditText().getText().toString());
                    nuevoContacto.setTelefono(txtTelefon.getEditText().getText().toString());
                    nuevoContacto.setCorreo(txtCorreo.getEditText().getText().toString());
                    listaAgenda.add(nuevoContacto);
                    archivo.escribir(listaAgenda);
                    AlertaUtil.mostrarAlerta("Registro de contacto","El contacto se ha registrado correctamente",
                            aceptarDialog,null,NuevoActivity.this);
                } catch(IOException ioe){

                }
            }
        });
    }


}
