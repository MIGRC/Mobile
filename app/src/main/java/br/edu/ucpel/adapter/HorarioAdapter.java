package br.edu.ucpel.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.edu.ucpel.R;
import br.edu.ucpel.bean.Horario;

/**
 *
 * @author Miguel Aguiar Barbosa
 */
public class HorarioAdapter extends ArrayAdapter<Horario> {

    private final Activity context;
    public Horario[] dados = new Horario[]{
            new Horario("Estrutura de Dados", "Sala 233C", "Terça 19:15"),
            new Horario("DOO III", "Sala 232C", "Terça 20:30"),
            new Horario("DOO II", "Sala 232C", "Quarta 19:15")};


    public HorarioAdapter(Activity context) {

        super(context, R.layout.item_list_view, (Horario[]) null);
        this.context = context;

  }/*

    public View getView(int posicao, View view, ViewGroup group) {

        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.item_list_view, null);

        TextView titulo = (TextView) item.findViewById(R.id.txtTitulo);
        titulo.setText(dados[posicao].getDisciplina());

        TextView subTitulo = (TextView) item.findViewById(R.id.txtSubTitulo);
        titulo.setText(dados[posicao].getSala());

        return item;
    }*/



}