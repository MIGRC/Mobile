package br.edu.ucpel.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.edu.ucpel.R;
import br.edu.ucpel.util.Mensagem;

public class MenuActivity extends ActionBarActivity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);
        }

        public void onClickNotas(View view){
            startActivity(new Intent(this, NotasActivity.class));
        }

        public void onClickHorarios(View view){
            startActivity(new Intent(this, HorariosActivity.class));
        }

        public void onClickMatricula(View view){
            startActivity(new Intent(this, MatriculaActivity.class));
        }

        public void onClickAvaliacoes(View view){
            startActivity(new Intent(this, AvaliacaoActivity.class));
        }

        public void onClickFrequencia(View view){
            startActivity(new Intent(this, FrequenciaActivity.class));
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()){
                case R.id.action_menu_trocar_matricula:
                    startActivity(new Intent(this, EscolhaMatriculaActivity.class));
                    finish();
                    break;
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
    }