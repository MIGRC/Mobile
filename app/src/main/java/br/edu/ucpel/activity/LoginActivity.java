package br.edu.ucpel.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import br.edu.ucpel.R;
import br.edu.ucpel.dao.UsuarioDAO;
import br.edu.ucpel.db.Conexoes;
import br.edu.ucpel.service.AlunoService;
import br.edu.ucpel.service.LoginService;
import br.edu.ucpel.service.ServicoService;
import br.edu.ucpel.util.Mensagem;


public class LoginActivity extends ActionBarActivity {

    private EditText edtUsuario, edtSenha;
    private UsuarioDAO usuarioDAO;
    private CheckBox ckbConectado;
    private Conexoes conexoes;
    private ProgressDialog dialog;

    private static final String MANTER_CONECTADO = "manter_conectado";
    private static final String PREFERENCE_NAME  = "LoginActivityPreferences";

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
                boolean servico = false;

                try {
                    servico = new ServicoService().execute().get();

                } catch (Exception ex) {
                    servico = false;
                }


                if (servico) {
                    try {
                        resultado = new LoginService(etLogin.getText().toString(), etSenha.getText().toString()).execute().get();
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

                        boolean insertAlunos = false;

                        try {
                            this.dialog = ProgressDialog.show(this, "Sincronizando", "Por favor, aguarde...", false, true);
                            insertAlunos = new AlunoService(etLogin.getText().toString(), this).execute().get();

                            if (insertAlunos) {
                                dialog.dismiss();
                                this.isEscolhaMatricula();
                            } else {
                                dialog.dismiss();
                                Mensagem.Msg(this, getString(R.string.msg_erro_sincronismo));
                            }

                        } catch (Exception ex) {
                            ex.getMessage();
                            insertAlunos = false;
                        }

                    } else {
                        Mensagem.Msg(this, getString(R.string.msg_login_incorreto));
                    }
                } else {
                    Mensagem.Msg(this, getString(R.string.msg_sem_webservice));
                }
            } else {
                Mensagem.Msg(this, getString(R.string.msg_sem_conexao));
            }
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

}