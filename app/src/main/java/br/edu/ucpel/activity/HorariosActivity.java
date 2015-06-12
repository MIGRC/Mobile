package br.edu.ucpel.activity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import br.edu.ucpel.R;
import br.edu.ucpel.adapter.HorarioAdapter;
import br.edu.ucpel.bean.Aluno;
import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.dao.AlunoDAO;
import br.edu.ucpel.dao.HorarioDAO;
import br.edu.ucpel.service.HorarioService;
import br.edu.ucpel.db.Conexoes;
import br.edu.ucpel.service.ServicoService;
import br.edu.ucpel.util.Mensagem;

public class HorariosActivity extends ActionBarActivity {

    private ListView lista;
    private List<Horario> horarioList;
    private HorarioAdapter horarioAdapter;
    private HorarioDAO horarioDAO;
    private ProgressDialog dialog;
    private AlunoDAO alunoDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        lista = (ListView) findViewById(R.id.lvHorarios);

        this.atualizarListaHorarios();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_horarios, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_menu_sincronizar_horario:
                this.sincronismo();
                break;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
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
                    resultado = new HorarioService(alunoDAO.selectCursoAlunoId(), this).execute().get();

                    if (resultado) {
                        dialog.dismiss();
                        this.atualizarListaHorarios();
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

    public void atualizarListaHorarios() {
        horarioDAO = new HorarioDAO(this);
        horarioList = horarioDAO.listarHorarios();

        horarioAdapter = new HorarioAdapter(this, horarioList);

        lista.setAdapter(horarioAdapter);
    }
}
