package br.edu.ucpel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.ucpel.adapter.ExpandableListAdapter;
import br.edu.ucpel.bean.Avaliacao;
import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.dao.AvaliacaoDAO;


public class AvaliacaoActivity extends ActionBarActivity {

  /*  private ListView lista;
    private List<Avaliacao> avalicaoList;
    public HashMap<Avaliacao, List<Avaliacao>> descricaoList;
//    private AvaliacaoAdapter avaliacaoAdapter;
    private ExpandableListAdapter avaliacaoAdapter;
    private AvaliacaoDAO avaliacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        avaliacaoDAO = new AvaliacaoDAO(this);
        avalicaoList = avaliacaoDAO.listarAvaliacoes();
        //avaliacaoAdapter = new AvaliacaoAdapter(this, avalicaoList);
        avaliacaoAdapter = new ExpandableListAdapter(this, avalicaoList);

        lista = (ListView) findViewById(R.id.lvAvaliacoes);
        lista.setAdapter(avaliacaoAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_avaliacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}*/
  private ExpandableListAdapter listAdapter;
    private  ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private AvaliacaoDAO avaliacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintext);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        List<Avaliacao> avaList = avaliacaoDAO.listarAvaliacoes();

        int i = 0;

        for(Avaliacao avaliacao : avaList){

            listDataHeader.add(avaliacao.getDisciplina_nome());
            List<String> avaliacaolist = new ArrayList<String>();
            avaliacaolist.add(avaliacao.getAvaliacao());
            listDataChild.put(listDataHeader.get(i), avaliacaolist);
            /*for(Horario horario:horarios){
                List<Avaliacao> avaliacao=dao.listaAvaliacoes(disciplina,horario);

            }*/
            i++;
        }

    }
}
