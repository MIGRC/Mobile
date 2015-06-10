package br.edu.ucpel.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import br.edu.ucpel.R;
import br.edu.ucpel.adapter.AvaliacaoAdapter;
import br.edu.ucpel.bean.Avaliacao;
import br.edu.ucpel.dao.AvaliacaoDAO;

public class MatriculaActivity extends Activity {
    private List<String> listGroup;
    private HashMap<String, List<String>> listData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula);


        buildList();

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new AvaliacaoAdapter(MatriculaActivity.this, listGroup, listData));

        expandableListView.setOnChildClickListener(new OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MatriculaActivity.this, "Group: "+groupPosition+"| Item: "+childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new OnGroupExpandListener(){
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MatriculaActivity.this, "Group (Expand): "+groupPosition, Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener(){
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(MatriculaActivity.this, "Group (Collapse): "+groupPosition, Toast.LENGTH_SHORT).show();
            }
        });

        //expandableListView.setGroupIndicator(getResources());
    }

    public void buildList(){
        listGroup = new ArrayList<String>();
        listData = new HashMap<String, List<String>>();

        AvaliacaoDAO disciplinaDAO = new AvaliacaoDAO(this);
        List<String> avaliacaolist = new ArrayList<String>();
        //List<Horario> horarioList = horarioDAO.listarHorarios();

        int i = 0;

        for (Avaliacao a : disciplinaDAO.listarDisciplina()) {

            listGroup.add(a.getDisciplina_nome());



            AvaliacaoDAO disciplinaDAO2 = new AvaliacaoDAO(this);



            for (Avaliacao a2 : disciplinaDAO2.listarAvaliacoesPorDisciplina(a.getDisciplina_id())) {
                avaliacaolist.add(a2.getAvaliacao());
            }



            listData.put(listGroup.get(i), avaliacaolist);

            i ++;
        }

        /*for (int e = 0; e <=i2; e++) {
            listData.put(listGroup.get(e), avaliacaolist);
        }*/



    }
}
