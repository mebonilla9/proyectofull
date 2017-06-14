package co.appreactor.proyectofull.negocio;

import android.Manifest;
import android.content.DialogInterface;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.modelo.entidades.Agenda;
import co.appreactor.proyectofull.modelo.servicios.agenda.Archivador;
import co.appreactor.proyectofull.modelo.servicios.agenda.ArchivoJson;
import co.appreactor.proyectofull.negocio.adaptadores.AdaptadorAgenda;
import co.appreactor.proyectofull.negocio.util.AlertaUtil;

public class ListaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextInputLayout txtBuscar;
    private ListView lstContactos;

    private Archivador archivo;

    private final String[] permisos = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int codigoPermiso = 33;

    private List<Agenda> listaAgenda;
    private List<Agenda> listaTemporal;


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
            Snackbar.make(fab, "No existe información de contactos", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void asignarEventos() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaActivity.this, NuevoActivity.class));
            }
        });

        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                visualizarContacto(position);
            }
        });

        lstContactos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                DialogInterface.OnClickListener confirmarEliminacion = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminarContacto(position);
                    }
                };

                AlertaUtil.mostrarAlerta(
                        "Eliminación de contacto",
                        "Esta seguro de eliminar este contacto",
                        confirmarEliminacion,
                        null,ListaActivity.this
                );
                return true;
            }
        });

        txtBuscar.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filtrarLista(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void filtrarLista(CharSequence charSequence) {
        listaTemporal = new ArrayList<>();
        for (Agenda contacto : listaAgenda){
            if (contacto.getNombre().toLowerCase().contains(charSequence.toString().toLowerCase())){
                listaTemporal.add(contacto);
            }
        }
        lstContactos.setAdapter(new AdaptadorAgenda(ListaActivity.this,listaTemporal));
    }

    private void eliminarContacto(int position) {
        try {
            if (listaTemporal != null){
                Agenda contactoTemporal = listaTemporal.get(position);
                for(int i = 0; i < listaAgenda.size(); i++){
                    if (contactoTemporal.equals(listaAgenda.get(i))){
                        listaAgenda.remove(contactoTemporal);
                        break;
                    }
                }
            } else {
                listaAgenda.remove(position);
            }
            archivo.escribir(listaAgenda);
            llenarLista();
        } catch (IOException e) {
            Snackbar.make(fab,"El contacto no ha podido ser eliminado",Snackbar.LENGTH_LONG).show();
        }
    }

    private void visualizarContacto(int position) {
        Intent irDetalle = new Intent(ListaActivity.this,DetalleActivity.class);
        Agenda contactoEnviar;
        if (listaTemporal != null){
            contactoEnviar = listaTemporal.get(position);
        } else {
            contactoEnviar = listaAgenda.get(position);
        }
        irDetalle.putExtra("contacto",contactoEnviar);
        startActivity(irDetalle);
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
