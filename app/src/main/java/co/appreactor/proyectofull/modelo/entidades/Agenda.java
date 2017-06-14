package co.appreactor.proyectofull.modelo.entidades;

import java.io.Serializable;

/**
 * Created by Capacitaciones_pc7 on 14/06/2017.
 */

public class Agenda implements Serializable{

    private String nombre;
    private String telefono;
    private String correo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
