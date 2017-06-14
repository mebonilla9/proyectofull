package co.appreactor.proyectofull.negocio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.modelo.entidades.Agenda;
import co.appreactor.proyectofull.modelo.servicios.agenda.Archivador;
import co.appreactor.proyectofull.modelo.servicios.agenda.ArchivoJson;
import co.appreactor.proyectofull.negocio.adaptadores.AdaptadorAgenda;

public class ListaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextInputLayout txtBuscar;
    private ListView lstContactos;

    private Archivador archivo;

    private final String[] permisos = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int codigoPermiso = 33;

    private List<Agenda> listaAgenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.txtBuscar = (TextInputLayout) findViewById(R.id.txtBuscar);
        this.lstContactos = (ListView) findViewById(R.id.lstContactos);
        setSupportActionBar(toolbar);
        this.archivo = new ArchivoJson(ListaActivity.this);
        asignarEventos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        validarllenarLista();
    }

    private void validarllenarLista() {
        /**
         * Validar que tengamos permisos para acceder al almacenamiento externo
         */
        int permisoConcedido = ContextCompat.checkSelfPermission(
                ListaActivity.this, permisos[0]);
        if (permisoConcedido == PackageManager.PERMISSION_GRANTED) {
            llenarLista();
            return;
        }
        ActivityCompat.requestPermissions(ListaActivity.this, permisos, codigoPermiso);
    }

    private void llenarLista() {
        try {
            listaAgenda = this.archivo.leer();
            AdaptadorAgenda adaptador = new AdaptadorAgenda(ListaActivity.this, listaAgenda);
            lstContactos.setAdapter(adaptador);
        } catch (IOException e) {
            Snackbar.make(fab, "No existe información de contactos",Snackbar.LENGTH_SHORT).show();
        }
    }

    private void asignarEventos() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaActivity.this, NuevoActivity.class));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == codigoPermiso &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            llenarLista();
        }
    }
}
