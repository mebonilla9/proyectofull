package co.appreactor.proyectofull.modelo.dto;

/**
 * Created by Capacitaciones_pc7 on 27/06/2017.
 */

public class RespuestaDTO {

    private int codigo;
    private String mensaje;
    private Object datos;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }
}
