package br.edu.ucpel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import br.edu.ucpel.adapter.ImageAdapter;

public class MenuActivity extends Activity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);
        }

        public void onClickNotas(View view){
            startActivity(new Intent(this, NotasActivity.class));
            finish();
        }

        public void onClickHorarios(View view){
            startActivity(new Intent(this, HorariosActivity.class));
            finish();
        }
    }