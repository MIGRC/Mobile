package br.edu.ucpel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import br.edu.ucpel.adapter.AvaliacaoAdapter;
import br.edu.ucpel.adapter.MatriculaAdapter;
import br.edu.ucpel.bean.Avaliacoes;
import br.edu.ucpel.bean.Matricula;
import br.edu.ucpel.dao.AvaliacaoDAO;
import br.edu.ucpel.dao.MatriculaDAO;


public class AvaliacaoActivity extends ActionBarActivity {

    private ListView lista;
    private List<Avaliacoes> avalicaoList;
    private AvaliacaoAdapter avaliacaoAdapter;
    private AvaliacaoDAO avaliacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        avaliacaoDAO = new AvaliacaoDAO(this);
        avalicaoList = avaliacaoDAO.listarAvaliacoes();
        avaliacaoAdapter = new AvaliacaoAdapter(this, avalicaoList);

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
}
