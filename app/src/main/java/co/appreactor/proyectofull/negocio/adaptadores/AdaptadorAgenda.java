package co.appreactor.proyectofull.negocio.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import co.appreactor.proyectofull.R;
import co.appreactor.proyectofull.modelo.entidades.Agenda;

/**
 * Created by Capacitaciones_pc7 on 14/06/2017.
 */

public class AdaptadorAgenda extends BaseAdapter {

    private Context contexto;
    private List<Agenda> listaAgenda;

    public AdaptadorAgenda(Context contexto, List<Agenda> listaAgenda) {
        this.contexto = contexto;
        this.listaAgenda = listaAgenda;
    }

    @Override
    public int getCount() {
        return listaAgenda.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAgenda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(contexto)
                    .inflate(R.layout.item_lista,null);
        }
        return null;
    }
}
