package br.edu.ucpel.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.edu.ucpel.R;
import br.edu.ucpel.adapter.AlunoAdapter;
import br.edu.ucpel.bean.Aluno;
import br.edu.ucpel.bean.Matricula;
import br.edu.ucpel.dao.AlunoDAO;
import br.edu.ucpel.dao.AvaliacaoDAO;
import br.edu.ucpel.dao.FrequenciaDAO;
import br.edu.ucpel.dao.HorarioDAO;
import br.edu.ucpel.dao.MatriculaDAO;
import br.edu.ucpel.db.Conexoes;
import br.edu.ucpel.service.AvaliacaoService;
import br.edu.ucpel.service.FrequenciaService;
import br.edu.ucpel.service.HorarioService;
import br.edu.ucpel.service.MatriculaService;
import br.edu.ucpel.service.ServicoService;
import br.edu.ucpel.util.Mensagem;


public class EscolhaMatriculaActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private ListView lista;
    private List<Aluno> alunoList;
    private AlunoAdapter alunoAdapter;
    private AlunoDAO alunoDAO;
    private MatriculaActivity matriculaActivity = new MatriculaActivity();
    private NotasActivity notasActivity = new NotasActivity();
    private AvaliacaoActivity avaliacaoActivity = new AvaliacaoActivity();
    private FrequenciaActivity frequenciaActivity = new FrequenciaActivity();
    private HorariosActivity horariosActivity = new HorariosActivity();
    private int idposicao;
    private ProgressDialog dialog;


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

        switch (i){
            case 0:
                this.sincronizaTudo();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_escolha_matricula, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_menu_sair:
                Mensagem.MsgConfirm(this, "Sair do sistema", "Deseja realmente sair?", R.drawable.ic_action_about, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                break;
            case R.id.action_menu_logout:
                SharedPreferences preferences   = getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void  sincronizaTudo(){
        int id = alunoList.get(idposicao).get_id();

        boolean resultado = false;
        boolean servico = false;

        if(Conexoes.isOnline(this)) {
            try{
                servico = new ServicoService().execute().get();

            }catch (Exception ex){
                servico = false;
            }

            if(servico) {
                try {
                    this.dialog = ProgressDialog.show(this, "Sincronizando", "Por favor, aguarde...", false, true);
                    alunoDAO = new AlunoDAO(this);
                    alunoDAO.updateGeralAluno();
                    alunoDAO.updateAluno(id);
                    resultado = new MatriculaService(alunoDAO.selectCursoAlunoId(), this).execute().get();
                    resultado = new AvaliacaoService(alunoDAO.selectCursoAlunoId(), this).execute().get();
                    resultado = new FrequenciaService(alunoDAO.selectCursoAlunoId(), this).execute().get();
                    resultado = new HorarioService(alunoDAO.selectCursoAlunoId(), this).execute().get();
                    resultado = true;

                   if(resultado){
                        dialog.dismiss();
                        Intent intent = new Intent(this, MenuActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Mensagem.Msg(this, getString(R.string.msg_erro_sincronismo));
                    }

                } catch (Exception ex) {
                    ex.getMessage();
                    resultado = false;
                }
            }
            else{
                Mensagem.Msg(this, getString(R.string.msg_sem_webservice));
            }
        }
        else{
            Mensagem.Msg(this, getString(R.string.msg_sem_conexao));
        }

    }
}
