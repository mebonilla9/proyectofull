package co.appreactor.proyectofull.modelo.servicios.pokemon;

import java.util.List;

import co.appreactor.proyectofull.modelo.dto.PokemonRespuesta;
import co.appreactor.proyectofull.modelo.entidades.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Capacitaciones_pc7 on 05/07/2017.
 */

public interface PokemonCall {

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);

}
