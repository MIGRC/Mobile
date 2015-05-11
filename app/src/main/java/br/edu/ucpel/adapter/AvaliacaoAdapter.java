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
import br.edu.ucpel.bean.Avaliacoes;
import br.edu.ucpel.bean.Matricula;

/**
 * Created by miguel on 11/05/15.
 */
public class AvaliacaoAdapter extends BaseAdapter{

    private Context context;
    private List<Avaliacoes> listaAvalicao;

    public AvaliacaoAdapter(Context ctx, List<Avaliacoes> avaliacoes) {
        this.context = ctx;
        this.listaAvalicao = avaliacoes;
    }

    @Override
    public int getCount() {
        return listaAvalicao.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAvalicao.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Avaliacoes avaliacoes = listaAvalicao.get(position);

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_view, null);
        }

        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(avaliacoes.getDisciplina());

        TextView txtSubTitulo1 = (TextView) view.findViewById(R.id.txtSubTitulo1);
        txtSubTitulo1.setText(avaliacoes.getAvaliacao());

        TextView txtSubTitulo2 = (TextView) view.findViewById(R.id.txtSubTitulo2);
        txtSubTitulo2.setText(avaliacoes.getData());

        return  view;
    }
}
