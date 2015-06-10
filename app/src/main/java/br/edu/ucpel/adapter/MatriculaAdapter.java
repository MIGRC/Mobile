package br.edu.ucpel.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ucpel.R;
import br.edu.ucpel.bean.Matricula;

/**
 * Created by Miguel Aguiar Barbosa on 11/05/15.
 */
public class MatriculaAdapter extends BaseAdapter {

    private Context context;
    private List<Matricula> listaMatricula;

    public MatriculaAdapter(Context ctx, List<Matricula> matriculas) {
        this.context = ctx;
        this.listaMatricula = matriculas;
    }

    @Override
    public int getCount() {
        return listaMatricula.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMatricula.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Matricula matricula = listaMatricula.get(position);

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_three_view, null);
        }

        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(matricula.getDisciplina_nome());

        TextView txtSubTitulo1 = (TextView) view.findViewById(R.id.txtSubTitulo1);
        txtSubTitulo1.setText(matricula.getSituacao());

        TextView txtSubTitulo2 = (TextView) view.findViewById(R.id.txtSubTitulo2);
        txtSubTitulo2.setText(matricula.getTurma());

        return view;
    }
}
