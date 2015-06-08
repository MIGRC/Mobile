package br.edu.ucpel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import br.edu.ucpel.dao.UsuarioDAO;
import br.edu.ucpel.util.Conexoes;
import br.edu.ucpel.util.Mensagem;
import br.edu.ucpel.ws.ClienteGSON;


public class LoginActivity extends ActionBarActivity {

    private EditText edtUsuario, edtSenha;
    private UsuarioDAO usuarioDAO;
    private CheckBox ckbConectado;
    private Conexoes conexoes;

    private static final String MANTER_CONECTADO = "manter_conectado";
    private static final String PREFERENCE_NAME  = "LoginActivityPreferences";

/*    private Handler manipulador = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            atualiza();
        }
    };
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario   = (EditText) findViewById(R.id.login_edtUsuario);
        edtSenha     = (EditText) findViewById(R.id.login_edtSenha);
        ckbConectado = (CheckBox) findViewById(R.id.login_ckbConectado);

        usuarioDAO = new UsuarioDAO(this);

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        boolean conectado             = preferences.getBoolean(MANTER_CONECTADO, false);

        if(conectado){
            isLogar();
        }
    }

    public void onClickLogar(View view){
        EditText etLogin = (EditText) edtUsuario;
        EditText etSenha = (EditText) edtSenha;

        boolean validacao = true;

        if(conexoes.isOnline(this)) {

            if (etLogin == null || etLogin.equals("")) {
                validacao = false;
                edtUsuario.setError(getString(R.string.login_valUsuario));
            } else if (edtSenha == null || edtSenha.equals("")) {
                validacao = false;
                edtSenha.setError(getString(R.string.login_valSenha));
            } else if (validacao) {

                boolean resultado = false;

                try {
                    resultado = new ClienteGSON(etLogin.getText().toString(), etSenha.getText().toString()).execute().get();
                } catch (Exception ex) {
                    ex.getMessage();
                    resultado = false;
                }

                //logar
                if (resultado) {
                    if (ckbConectado.isChecked()) {
                        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putBoolean(MANTER_CONECTADO, true);
                        editor.commit();
                    }

                    this.isEscolhaMatricula();
                } else {
                    Mensagem.Msg(this, getString(R.string.msg_login_incorreto));
                }
            }
        }
        else{
            Mensagem.Msg(this, getString(R.string.msg_sem_conexao));
        }
    }

    private void isEscolhaMatricula(){
        startActivity(new Intent(this, EscolhaMatriculaActivity.class));
        finish();
    }

    private void isLogar(){
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
          if (id == R.id.action_settings) {
               return true;
          }
        return super.onOptionsItemSelected(item);
    }

}