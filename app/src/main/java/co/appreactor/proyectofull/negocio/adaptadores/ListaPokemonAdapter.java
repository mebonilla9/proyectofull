package co.appreactor.proyectofull.negocio.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.modelo.entidades.Pokemon;

/**
 * Created by Capacitaciones_pc7 on 28/06/2017.
 */

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder>{

    private List<Pokemon> dataSource;
    private Context contexto;

    public ListaPokemonAdapter(Context contexto) {
        this.dataSource = new ArrayList<>();
        this.contexto = contexto;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon,parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon pokemon = dataSource.get(position);

        holder.txtNombrePokemon.setText(pokemon.getName());
        Glide.with(contexto)
                .load("http://pokeapi.co/media/sprites/pokemon/"+pokemon.getNumber()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgPokemon);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void adicionarElementos(List<Pokemon> listaPokemons){
        this.dataSource.addAll(listaPokemons);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgPokemon;
        private TextView txtNombrePokemon;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgPokemon = (ImageView) itemView.findViewById(R.id.imgPokemon);
            this.txtNombrePokemon = (TextView) itemView.findViewById(R.id.txtNombrePokemon);
        }
    }

}
