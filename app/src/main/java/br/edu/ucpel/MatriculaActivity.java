package br.edu.ucpel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import br.edu.ucpel.adapter.MatriculaAdapter;
import br.edu.ucpel.bean.Matricula;
import br.edu.ucpel.dao.MatriculaDAO;


public class MatriculaActivity extends ActionBarActivity {

    private ListView lista;
    private List<Matricula> matriculaList;
    private MatriculaAdapter matriculaAdapter;
    private MatriculaDAO matriculaDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula);

        matriculaDAO = new MatriculaDAO(this);
        matriculaList = matriculaDAO.listarMatriculas();
        matriculaAdapter = new MatriculaAdapter(this, matriculaList);

        lista = (ListView) findViewById(R.id.lvMatriculas);
        lista.setAdapter(matriculaAdapter);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_escolha_matricula, menu);
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
    }*/
}
