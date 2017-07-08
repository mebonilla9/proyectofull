package co.appreactor.proyectofull.negocio.actividades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.Serializable;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.modelo.entidades.Agenda;

public class DetalleActivity extends AppCompatActivity {

    private TextView txtDetalleNombre;
    private TextView txtDetalleTelefono;
    private TextView txtDetalleCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        this.txtDetalleCorreo = (TextView) findViewById(R.id.txtDetalleCorreo);
        this.txtDetalleTelefono = (TextView) findViewById(R.id.txtDetalleTelefono);
        this.txtDetalleNombre = (TextView) findViewById(R.id.txtDetalleNombre);

        Agenda contactoRecibido = (Agenda) getIntent().getSerializableExtra("contacto");
        txtDetalleCorreo.setText(contactoRecibido.getCorreo());
        txtDetalleTelefono.setText(contactoRecibido.getTelefono());
        txtDetalleNombre.setText(contactoRecibido.getNombre());
    }
}
