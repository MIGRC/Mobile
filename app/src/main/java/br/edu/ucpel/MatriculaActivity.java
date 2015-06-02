package br.edu.ucpel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ucpel.adapter.MatriculaAdapter;
import br.edu.ucpel.bean.Matricula;
import br.edu.ucpel.dao.MatriculaDAO;
import br.edu.ucpel.ws.ClienteGSON;


public class MatriculaActivity extends ActionBarActivity {

    private ListView lista;
    private List<Matricula> matriculaList;
    private MatriculaAdapter matriculaAdapter;
    private MatriculaDAO matriculaDAO;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula);

        matriculaDAO = new MatriculaDAO(this);
       // matriculaList = matriculaDAO.listarMatriculas();
        matriculaAdapter = new MatriculaAdapter(this, matriculaList);

        lista = (ListView) findViewById(R.id.lvMatriculas);
        lista.setAdapter(matriculaAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_matricula, menu);

        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_sincronizar:
                this.dialog = ProgressDialog.show(this, "Sincronizando", "Por favor, aguarde...". false, true);
               // Sincronismo();
                break;
            case R.id.action_menu_sair:
                finish();
                startActivity(new Intent(this, MenuActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class Sincronismo() {

        int etCursoAlunoId = 1;//(EditText) findViewById(R.id.curso_aluno_id);

        ClienteGSON cliente = new ClienteGSON();

        try {
           // Matricula matriculaGet = cliente.matriculaGet(etCursoAlunoId.intValue());

           /* if(testeGet.isLogado()){
                Toast.makeText(this, "autorizdo", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "sai fora!", Toast.LENGTH_SHORT).show();
            }*/

      /*  } catch (Exception ex) {
            Log.w("Principal", "Erro", ex);
        }
    }*/
}
