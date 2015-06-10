package br.edu.ucpel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import br.edu.ucpel.R;
import br.edu.ucpel.adapter.ImageAdapter;


public class MainActivity extends Activity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] lista = new int[]{
                R.drawable.grande,
                R.drawable.grande,
                R.drawable.grande,
                R.drawable.grande,
                R.drawable.grande
        };

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

       /* if (id == R.id.action_lista_usuarios) {
            startActivity(new Intent(this, ListUsuariosActivity.class));
        }*/
        return super.onOptionsItemSelected(item);
    }

}