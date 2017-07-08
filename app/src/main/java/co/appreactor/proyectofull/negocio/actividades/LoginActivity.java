package co.appreactor.proyectofull.negocio.actividades;

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
import co.appreactor.proyectofull.negocio.util.CryptoUtil;
import co.appreactor.proyectofull.negocio.util.PreferenciasUtil;

public class LoginActivity extends AppCompatActivity implements Handler.Callback{

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
        this.handler = new Handler(this);
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
    }

    private void resultadoLogin(Object obj) {
        Type tipoRespuesta = new TypeToken<RespuestaDTO<Usuario>>(){}.getType();
        RespuestaDTO<Usuario> respuesta = new Gson().fromJson(obj.toString(),tipoRespuesta);
        if (respuesta.getCodigo() != 1){
            Toast.makeText(this, respuesta.getMensaje(), Toast.LENGTH_LONG).show();
            return;
        }
        if (respuesta.getDatos() != null){
            Toast.makeText(this, "Bienvenido: "+respuesta.getDatos().getNombre(), Toast.LENGTH_LONG).show();
            PreferenciasUtil.guardarPreferencias("nombreUsuario",respuesta.getDatos().getNombre(),LoginActivity.this);
            PreferenciasUtil.guardarPreferencias("correoUsuario",respuesta.getDatos().getCorreo(),LoginActivity.this);
            PreferenciasUtil.guardarPreferencias("contrasenaUsuario",respuesta.getDatos().getContrasena(),LoginActivity.this);
            PreferenciasUtil.guardarPreferencias("idUsuario",respuesta.getDatos().getIdUsuario().toString(),LoginActivity.this);
            PreferenciasUtil.guardarPreferencias("estadoUsuario",respuesta.getDatos().getEstado().toString(),LoginActivity.this);
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    private String generarDataLogin(String correo, String contrasena) {
        StringBuilder constructorData = new StringBuilder();
        constructorData.append("correo=");
        constructorData.append(correo);
        constructorData.append("&contrasena=");
        constructorData.append(CryptoUtil.cifrarSha384(contrasena));
        return constructorData.toString();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                resultadoLogin(msg.obj);
                break;
        }
        return false;
    }
}
