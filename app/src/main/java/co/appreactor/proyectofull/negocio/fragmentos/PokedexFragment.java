package co.appreactor.proyectofull.negocio.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.modelo.dto.PokemonRespuesta;
import co.appreactor.proyectofull.modelo.entidades.Pokemon;
import co.appreactor.proyectofull.modelo.servicios.pokemon.PokemonCall;
import co.appreactor.proyectofull.negocio.adaptadores.ListaPokemonAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Capacitaciones_pc7 on 05/07/2017.
 */

public class PokedexFragment extends Fragment {

    private Retrofit servicio;
    private int offset;
    private final int limit = 20;
    private boolean aptoParaCargar;

    private RecyclerView rvPokedexView;

    private ListaPokemonAdapter adaptador;
    private GridLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_pokedex, container, false);
        this.rvPokedexView = (RecyclerView) vista.findViewById(R.id.rvPokedexView);
        return vista;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Configurar retrofit para conexion con el servidor
        this.servicio = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // configurar adapter y layout manager
        adaptador = new ListaPokemonAdapter(getActivity());
        rvPokedexView.setAdapter(adaptador);
        rvPokedexView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),3);
        rvPokedexView.setLayoutManager(layoutManager);
        asignarEventos();
        obtenerDatos(offset);
    }

    private void asignarEventos() {
        RecyclerView.OnScrollListener desplazamiento = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0){
                    int itemsVisibles = layoutManager.getChildCount();
                    int totalItems = layoutManager.getItemCount();
                    int itemsDesplazados = layoutManager.findFirstVisibleItemPosition();
                    if (aptoParaCargar){
                        if ((itemsDesplazados+itemsVisibles) >= totalItems){
                            aptoParaCargar = false;
                            offset += limit;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        };
        rvPokedexView.addOnScrollListener(desplazamiento);
    }

    private void obtenerDatos(int offset) {
        Callback<PokemonRespuesta> respuestaCallback = new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if (!response.isSuccessful()){
                    // logica del negocio
                    return;
                }

                ArrayList<Pokemon> listaPokemon = response.body().getResults();
                aptoParaCargar = true;
                adaptador.adicionarElementos(listaPokemon);

            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Log.e("POKEDEX",t.getMessage());
            }
        };
        Call<PokemonRespuesta> peticion = servicio.create(PokemonCall.class).obtenerListaPokemon(limit, offset);
        peticion.enqueue(respuestaCallback);
    }
}
