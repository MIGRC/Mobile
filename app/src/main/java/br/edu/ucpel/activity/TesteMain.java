package br.edu.ucpel.activity;


import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import br.edu.ucpel.R;
import br.edu.ucpel.adapter.ImageAdapter;

public class TesteMain extends ActionBarActivity {
    GridView grid;
    String[] web = {
            "Matrículas",
            "Notas",
            "Data Avaliações",
            "Frequencias",
            "Horários"
    } ;
    int[] imageId = {
            R.mipmap.teste,
            R.mipmap.teste2,
            R.mipmap.teste,
            R.mipmap.teste2,
            R.mipmap.teste,
            R.mipmap.teste2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_main);

        ImageAdapter adapter = new ImageAdapter(TesteMain.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(TesteMain.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }

}