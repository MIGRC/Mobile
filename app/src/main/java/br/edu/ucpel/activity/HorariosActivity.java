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
import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.dao.HorarioDAO;
import br.edu.ucpel.service.HorarioService;
import br.edu.ucpel.db.Conexoes;
import br.edu.ucpel.util.Mensagem;

public class HorariosActivity extends ActionBarActivity {

    private ListView lista;
    private List<Horario> horarioList;
    private HorarioAdapter horarioAdapter;
    private HorarioDAO horarioDAO;
    private ProgressDialog dialog;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        this.atualizarLista();

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

        if(Conexoes.isOnline(this)) {
            try {
                this.dialog = ProgressDialog.show(this, "Sincronizando", "Por favor, aguarde...", false, true);
                resultado = new HorarioService(1, this).execute().get();
                //hs.execute();

                if(resultado){
                    dialog.dismiss();
                    this.atualizarLista();
                } else {
                    Mensagem.Msg(this, getString(R.string.msg_erro_sincronismo));
                }

            } catch (Exception ex) {
                ex.getMessage();
                resultado = false;
            }
        }
        else{
            Mensagem.Msg(this, getString(R.string.msg_sem_conexao));
        }

    }

    private void atualizarLista() {
        horarioDAO = new HorarioDAO(this);
        horarioList = horarioDAO.listarHorarios();
        System.out.println(horarioList.size());
        if(horarioList.size() > 0){
            horarioAdapter = new HorarioAdapter(this, horarioList);
        }
        else{
            horarioAdapter = new HorarioAdapter(this, null);
        }

        lista = (ListView) findViewById(R.id.lvHorarios);
        lista.setAdapter(horarioAdapter);
    }
}
