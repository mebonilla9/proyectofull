package co.appreactor.proyectofull.negocio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.modelo.dto.RespuestaDTO;
import co.appreactor.proyectofull.modelo.entidades.Usuario;
import co.appreactor.proyectofull.modelo.servicios.peticiones.GestorPeticiones;

public class LoginActivity extends AppCompatActivity {

    private ImageView imageView3;
    private TextInputLayout txtCorreoLogin;
    private TextInputLayout txtContrasenaLogin;
    private Button btnIniciarSesion;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
        this.txtContrasenaLogin = (TextInputLayout) findViewById(R.id.txtContrasenaLogin);
        this.txtCorreoLogin = (TextInputLayout) findViewById(R.id.txtCorreoLogin);
        this.imageView3 = (ImageView) findViewById(R.id.imageView3);
        asignarEventos();
    }

    private void asignarEventos() {
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = generarDataLogin(
                        txtCorreoLogin.getEditText().getText().toString(),
                        txtContrasenaLogin.getEditText().getText().toString()
                );
                GestorPeticiones.enviarPeticion("login", data, handler);
            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        resultadoLogin(msg.obj);
                        break;
                }
                return false;
            }
        });
    }

    private void resultadoLogin(Object obj) {
        Type tipoRespuesta = new TypeToken<RespuestaDTO>(){}.getType();
        RespuestaDTO respuesta = new Gson().fromJson(obj.toString(),tipoRespuesta);
        if (respuesta.getCodigo() != 1){
            Toast.makeText(this, respuesta.getMensaje(), Toast.LENGTH_LONG).show();
            return;
        }
        Usuario usuario = (Usuario) respuesta.getDatos();
        if (usuario != null){
            Toast.makeText(this, "Bienvenido: "+usuario.getNombre(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    private String generarDataLogin(String correo, String contrasena) {
        StringBuilder constructorData = new StringBuilder("?");
        constructorData.append("correo=");
        constructorData.append(correo);
        constructorData.append("&contrasena=");
        constructorData.append(contrasena);
        return constructorData.toString();
    }
}
