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
import br.edu.ucpel.adapter.FrequenciaAdapter;
import br.edu.ucpel.bean.Frequencia;
import br.edu.ucpel.dao.AlunoDAO;
import br.edu.ucpel.dao.FrequenciaDAO;
import br.edu.ucpel.db.Conexoes;
import br.edu.ucpel.service.FrequenciaService;
import br.edu.ucpel.service.ServicoService;
import br.edu.ucpel.util.Mensagem;

/**
 * Created by Miguel Aguiar Barbosa on 12/06/15.
 */
public class FrequenciaActivity extends ActionBarActivity {

    private FrequenciaAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listGrupo;
    private HashMap<String, List<String>> listItensGrupo;
    private HashMap<String, List<String>> listItensGrupo2;
    private ProgressDialog dialog;
    private AlunoDAO alunoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequencia);

        expListView = (ExpandableListView) findViewById(R.id.elvFrequencia);

        this.atualizarListaFrequencias();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_frequencia, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_sincronizar_frequencia:
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
                    resultado = new FrequenciaService(alunoDAO.selectCursoAlunoId(), this).execute().get();

                    if(resultado){
                        dialog.dismiss();
                        this.atualizarListaFrequencias();
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

    public void atualizarListaFrequencias() {
        montaListFrequencia();

        listAdapter = new FrequenciaAdapter(this, listGrupo, listItensGrupo, listItensGrupo2);

        expListView.setAdapter(listAdapter);
    }

    private void montaListFrequencia() {
        try {
            listGrupo = new ArrayList<String>();
            listItensGrupo = new HashMap<String, List<String>>();
            listItensGrupo2 = new HashMap<String, List<String>>();
            FrequenciaDAO frequenciaDAO = new FrequenciaDAO(this);

            int i = 0;
            for (Frequencia f : frequenciaDAO.listarDisciplina()) {

                listGrupo.add(f.getDisciplina_nome());

                List<String> frequencialist = new ArrayList<String>();

                for (Frequencia f2 : frequenciaDAO.listarFrequenciaPorDisciplina(f.getDisciplina_id())) {
                    frequencialist.add(f2.getDt_falta());
                }

                List<String> frequencialist2 = new ArrayList<String>();

                for (Frequencia f3 : frequenciaDAO.listarFrequenciaPorDisciplina(f.getDisciplina_id())) {
                    frequencialist2.add(f3.getHr_falta());
                }

                listItensGrupo.put(listGrupo.get(i), frequencialist);
                listItensGrupo2.put(listGrupo.get(i), frequencialist2);

                i++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}