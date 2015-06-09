package br.edu.ucpel;


import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import br.edu.ucpel.adapter.HorarioAdapter;
import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.dao.HorarioDAO;
import br.edu.ucpel.service.HorarioService;
import br.edu.ucpel.util.Conexoes;
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
        horarioAdapter = new HorarioAdapter(this, horarioList);

        lista = (ListView) findViewById(R.id.lvHorarios);
        lista.setAdapter(horarioAdapter);
    }

    /*private void sincronismo() {

        try {
            List<Horario> horarioList = new HorarioService(1, this).execute().get();
            HorarioDAO horarioDAO = new HorarioDAO(this);
            horarioDAO.deleteGeral();
            for (Horario h : horarioList) {
                Horario horario = new Horario();
                horario.set_id(h.get_id());
                horario.setCurso_aluno_id(h.getCurso_aluno_id());
                horario.setDisciplina_id(h.getDisciplina_id());
                horario.setDisciplina_nome(h.getDisciplina_nome());
                horario.setSala(h.getSala());
                horario.setHorario(h.getHorario());
                horarioDAO.insert(horario);
                Log.i("HOTARIO LIST", h.getDisciplina_nome());
            }
        } catch (Exception ex) {
            Log.w("Principal", "Erro", ex);
        }
    }*/

   /* public class TituloAdapter extends ArrayAdapter<Horario> {

        private final Activity context;

        public TituloAdapter(Activity context) {

            super(context, R.layout.item_list_view, dados);
            this.context = context;
        }

        public View getView(int posicao, View view, ViewGroup group) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.item_list_view, null);

            TextView titulo = (TextView) item.findViewById(R.id.txtTitulo);
            titulo.setText(dados[posicao].getDisciplina());

            TextView subTitulo1 = (TextView) item.findViewById(R.id.txtSubTitulo1);
            subTitulo1.setText(dados[posicao].getSala());

            TextView subTitulo2 = (TextView) item.findViewById(R.id.txtSubTitulo2);
            subTitulo2.setText(dados[posicao].getHorario());

            return item;
        }
    }*/
}
