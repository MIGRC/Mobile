package br.edu.ucpel.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.ucpel.R;
import br.edu.ucpel.adapter.AvaliacaoAdapter;
import br.edu.ucpel.bean.Avaliacao;
import br.edu.ucpel.dao.AlunoDAO;
import br.edu.ucpel.dao.AvaliacaoDAO;
import br.edu.ucpel.db.Conexoes;
import br.edu.ucpel.service.AvaliacaoService;
import br.edu.ucpel.service.ServicoService;
import br.edu.ucpel.util.Mensagem;


public class AvaliacaoActivity extends ActionBarActivity {

    private AvaliacaoAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listGrupo;
    private HashMap<String, List<String>> listItensGrupo;
    private HashMap<String, List<String>> listItensGrupo2;
    private ProgressDialog dialog;
    AlunoDAO alunoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        expListView = (ExpandableListView) findViewById(R.id.elvAvaliacoes);

        this.atualizarListaAvaliacoes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_avaliacao, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_sincronizar_avaliacao:
                this.sincronismo();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void  sincronismo(){
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
                    resultado = new AvaliacaoService(alunoDAO.selectCursoAlunoId(), this).execute().get();

                    if(resultado){
                        dialog.dismiss();
                        this.atualizarListaAvaliacoes();
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

    public void atualizarListaAvaliacoes() {
        montaListAvaliacao();

        listAdapter = new AvaliacaoAdapter(this, listGrupo, listItensGrupo, listItensGrupo2);

        expListView.setAdapter(listAdapter);
    }

    private void montaListAvaliacao() {
        try {
            listGrupo = new ArrayList<String>();
            listItensGrupo = new HashMap<String, List<String>>();
            listItensGrupo2 = new HashMap<String, List<String>>();
            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(this);

            int i = 0;
            for (Avaliacao a : avaliacaoDAO.listarDisciplina()) {

                listGrupo.add(a.getDisciplina_nome());

                List<String> avaliacoeslist = new ArrayList<String>();

                for (Avaliacao a2 : avaliacaoDAO.listarAvaliacoesPorDisciplina(a.getDisciplina_id())) {
                    avaliacoeslist.add(a2.getAvaliacao());
                }

                List<String> avaliacoeslist2 = new ArrayList<String>();

                for (Avaliacao a3 : avaliacaoDAO.listarAvaliacoesPorDisciplina(a.getDisciplina_id())) {
                    avaliacoeslist2.add(a3.getData());
                }

                listItensGrupo.put(listGrupo.get(i), avaliacoeslist);
                listItensGrupo2.put(listGrupo.get(i), avaliacoeslist2);

                i++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
