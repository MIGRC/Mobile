package br.edu.ucpel.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ucpel.R;
import br.edu.ucpel.bean.Horario;

/**
 *
 * @author Miguel Aguiar Barbosa
 */
public class HorarioAdapter extends BaseAdapter {

    private Context context;
    private List<Horario> listaHorario;

    public HorarioAdapter(Context ctx, List<Horario> horarios) {
        this.context = ctx;
        this.listaHorario = horarios;
    }

    @Override
    public int getCount() {
        return listaHorario.size();
    }

    @Override
    public Object getItem(int position) {
        return listaHorario.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Horario horario = listaHorario.get(position);

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_view, null);

        }

        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(horario.getDisciplina());

        TextView txtSubTitulo1 = (TextView) view.findViewById(R.id.txtSubTitulo1);
        txtSubTitulo1.setText(horario.getSala());

        TextView txtSubTitulo2 = (TextView) view.findViewById(R.id.txtSubTitulo2);
        txtSubTitulo2.setText(horario.getHorario());

        return view;
    }
}