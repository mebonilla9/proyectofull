package co.appreactor.proyectofull.negocio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import co.appreactor.proyectofull.R;

public class ListaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        asignarEventos();
    }

    private void asignarEventos() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaActivity.this,MainActivity.class));
            }
        });
    }

}
