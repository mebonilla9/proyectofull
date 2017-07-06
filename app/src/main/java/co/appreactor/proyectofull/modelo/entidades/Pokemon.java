package co.appreactor.proyectofull.modelo.entidades;

/**
 * Created by Capacitaciones_pc7 on 05/07/2017.
 */

public class Pokemon {

    private int number;
    private String name;
    private String url; // permitir renderizar la imagen del pokemon con glide


    public int getNumber() {
        String[] partes = this.url.split("/");
        this.number = Integer.parseInt(partes[partes.length-1]);
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
