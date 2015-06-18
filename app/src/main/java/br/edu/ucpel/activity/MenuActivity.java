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
import android.widget.AdapterView;
import android.widget.GridView;

import br.edu.ucpel.R;
import br.edu.ucpel.adapter.MenuAdapter;
import br.edu.ucpel.util.Mensagem;

public class MenuActivity extends ActionBarActivity {

    GridView grid;
    String[] web = {
            "Matrículas",
            "Notas",
            "Data Avaliações",
            "Frequencias",
            "Horários"
    } ;
    int[] imageId = {
            R.mipmap.ic_matricula,
            R.mipmap.ic_notas,
            R.mipmap.ic_data_avaliacao,
            R.mipmap.ic_frequencia,
            R.mipmap.ic_horarios
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MenuAdapter adapter = new MenuAdapter(MenuActivity.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid_menu);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                switch (web[position]){
                    case "Matrículas":
                        startActivity(new Intent(getApplicationContext(), MatriculaActivity.class));
                        break;
                    case "Notas":
                        startActivity(new Intent(getApplicationContext(), NotasActivity.class));
                        break;
                    case "Data Avaliações":
                        startActivity(new Intent(getApplicationContext(), AvaliacaoActivity.class));
                        break;
                    case "Frequencias":
                        startActivity(new Intent(getApplicationContext(), FrequenciaActivity.class));
                        break;
                    case "Horários":
                        startActivity(new Intent(getApplicationContext(), HorariosActivity.class));
                        break;
                }
            }
        });
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
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}