package br.edu.ucpel;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.edu.ucpel.adapter.HorarioAdapter;
import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.dao.HorarioDAO;

public class HorariosActivity extends ActionBarActivity {

    private ListView lista;
    private List<Horario> horarioList;
    private HorarioAdapter horarioAdapter;
    private HorarioDAO horarioDAO;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        horarioDAO = new HorarioDAO(this);
        horarioList = horarioDAO.listarHorarios();
        horarioAdapter = new HorarioAdapter(this, horarioList);

        lista = (ListView) findViewById(R.id.lvHorarios);
        lista.setAdapter(horarioAdapter);

    }

   /* public class TituloAdapter extends ArrayAdapter<Horario> {

        private final Activity context;

        public TituloAdapter(Activity context) {

            super(context, R.layout.item_list_view, dados);
            this.context = context;
        }

        public View getView(int posicao, View view, ViewGroup group) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_list_view, null);

            TextView titulo = (TextView) item.findViewById(R.id.txtTitulo);
            titulo.setText(dados[posicao].getDisciplina());

            TextView subTitulo1 = (TextView) item.findViewById(R.id.txtSubTitulo1);
            subTitulo1.setText(dados[posicao].getSala());

            TextView subTitulo2 = (TextView) item.findViewById(R.id.txtSubTitulo2);
            subTitulo2.setText(dados[posicao].getHorario());

            return item;
        }
    }*/
}
