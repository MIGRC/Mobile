package br.edu.ucpel;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.List;

import br.edu.ucpel.adapter.AlunoAdapter;
import br.edu.ucpel.bean.Aluno;
import br.edu.ucpel.dao.AlunoDAO;


public class EscolhaMatriculaActivity extends ActionBarActivity {

    private ListView lista;
    private List<Aluno> alunoList;
    private AlunoAdapter alunoAdapter;
    private AlunoDAO alunoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_matricula);

        alunoDAO = new AlunoDAO(this);
        alunoList = alunoDAO.listarAlunos();
        alunoAdapter = new AlunoAdapter(this, alunoList);

        lista = (ListView) findViewById(R.id.lvAlunos);
        lista.setAdapter(alunoAdapter);
    }

}
