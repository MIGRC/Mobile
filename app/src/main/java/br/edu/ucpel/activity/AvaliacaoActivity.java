package br.edu.ucpel.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.ucpel.R;
import br.edu.ucpel.adapter.AvaliacaoAdapter;
import br.edu.ucpel.adapter.ExpandableListAdapter;
import br.edu.ucpel.bean.Avaliacao;
import br.edu.ucpel.dao.AvaliacaoDAO;


public class AvaliacaoActivity extends ActionBarActivity {

    private AvaliacaoAdapter listAdapter;
    private  ExpandableListView expListView;
    private List<String> listGrupo;
    private HashMap<String, List<String>> listItensGrupo;

    private List<String> listGroup;
    private HashMap<String, List<String>> listData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        montaListAvaliacao();

        listAdapter = new AvaliacaoAdapter(this, listGrupo, listItensGrupo);

        expListView.setAdapter(listAdapter);
    }

    private void montaListAvaliacao() {
        try {
            listGrupo = new ArrayList<String>();
            listItensGrupo = new HashMap<String, List<String>>();
            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(this);

            int i = 0;
            for (Avaliacao a : avaliacaoDAO.listarDisciplina()) {

                listGrupo.add(a.getDisciplina_nome());

                List<String> avaliacoeslist = new ArrayList<String>();

                for (Avaliacao a2 : avaliacaoDAO.listarAvaliacoesPorDisciplina(a.getDisciplina_id())) {
                    avaliacoeslist.add(a2.getAvaliacao());
                }

                listItensGrupo.put(listGrupo.get(i), avaliacoeslist);

                i++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
