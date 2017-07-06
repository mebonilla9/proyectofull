package co.appreactor.proyectofull.modelo.dto;

import java.util.ArrayList;

import co.appreactor.proyectofull.modelo.entidades.Pokemon;

/**
 * Created by Capacitaciones_pc7 on 05/07/2017.
 */

public class PokemonRespuesta {

    private ArrayList<Pokemon> results;


    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
