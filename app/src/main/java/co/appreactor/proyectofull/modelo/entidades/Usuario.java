package co.appreactor.proyectofull.modelo.entidades;

import java.io.Serializable;

/**
 * Created by Capacitaciones_pc7 on 27/06/2017.
 */

public class Usuario implements Serializable{

    private Long idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private Boolean estado;


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
