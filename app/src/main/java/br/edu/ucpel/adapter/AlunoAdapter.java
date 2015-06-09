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
import br.edu.ucpel.bean.Aluno;

/**
 * Created by Miguel Aguiar Barbosa on 09/06/15.
 */
public class AlunoAdapter extends BaseAdapter {

    private Context context;
    private List<Aluno> listaAluno;

    public AlunoAdapter(Context ctx, List<Aluno> alunos) {
        this.context = ctx;
        this.listaAluno = alunos;
    }

    @Override
    public int getCount() {
        return listaAluno.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAluno.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Aluno aluno = listaAluno.get(position);

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_one_list_view, null);

        }

        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(aluno.getChave());

        TextView txtSubTitulo1 = (TextView) view.findViewById(R.id.txtSubTitulo1);
        txtSubTitulo1.setText(aluno.getFlg_ativo());

        return view;
    }
}