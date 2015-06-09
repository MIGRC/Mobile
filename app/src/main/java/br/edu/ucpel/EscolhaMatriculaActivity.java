package br.edu.ucpel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.edu.ucpel.adapter.AlunoAdapter;
import br.edu.ucpel.bean.Aluno;
import br.edu.ucpel.dao.AlunoDAO;
import br.edu.ucpel.util.Mensagem;


public class EscolhaMatriculaActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private ListView lista;
    private List<Aluno> alunoList;
    private AlunoAdapter alunoAdapter;
    private AlunoDAO alunoDAO;
    private int idposicao;

    private AlertDialog alertDialog, alertConfirmacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_matricula);

        alunoDAO        = new AlunoDAO(this);
        alunoList       = alunoDAO.listarAlunos();
        alunoAdapter    = new AlunoAdapter(this, alunoList);
        alertDialog     = Mensagem.criarAlertDialog(this);

        lista = (ListView) findViewById(R.id.lvAlunos);
        lista.setAdapter(alunoAdapter);
        lista.setOnItemClickListener(this);
    }

    @Override
    public void onClick(DialogInterface dialog, int i) {
        int id = alunoList.get(idposicao).get_id();

        switch (i){
            case 0:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                alunoDAO.updateGeralAluno();
                alunoDAO.updateAluno(id);
                finish();
                break;
            case 1:
                alertConfirmacao.show();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                alertConfirmacao.dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idposicao = position;
        alertDialog.show();
    }
}
