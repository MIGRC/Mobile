package br.edu.ucpel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import br.edu.ucpel.adapter.ExpandableListAdapter;

public class NotasActivity extends ActionBarActivity {
    private List<String> listGroup;
    private HashMap<String, List<String>> listData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);


        buildList();

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new ExpandableListAdapter(NotasActivity.this, listGroup, listData));

        expandableListView.setOnChildClickListener(new OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(NotasActivity.this, "Group: "+groupPosition+"| Item: "+childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new OnGroupExpandListener(){
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(NotasActivity.this, "Group (Expand): "+groupPosition, Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener(){
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(NotasActivity.this, "Group (Collapse): "+groupPosition, Toast.LENGTH_SHORT).show();
            }
        });

        //expandableListView.setGroupIndicator(getResources().getDrawable(R.drawable.icon_group));
    }

    public void buildList(){
        listGroup = new ArrayList<String>();
        listData = new HashMap<String, List<String>>();

        // GROUP
        listGroup.add("Estrutura de Dados / Média 9.0");
        listGroup.add("DOO III / Média 8.5");
        listGroup.add("DOO II / Média 7.7");
        listGroup.add("Grupo 4");

        // CHILDREN
        List<String> auxList = new ArrayList<String>();
        auxList.add("1ª avaliação - 25/03/2014 - 10/8.5 = 8.5");
        auxList.add("2ª avaliação - 25/05/2014 - 6/10 = 6");
        auxList.add("Trabalho II - 25/05/2014 - 4/10 = 3.48");
        listData.put(listGroup.get(0), auxList);

        auxList = new ArrayList<String>();
        auxList.add("3ª avaliação - 25/03/2014 - 10/8.5 = 8.5");
        auxList.add("4ª avaliação - 25/05/2014 - 6/10 = 6");
        auxList.add("Trabalho III - 25/05/2014 - 4/10 = 3.48");
        listData.put(listGroup.get(1), auxList);

        auxList = new ArrayList<String>();
        auxList.add("3ª avaliação - 25/03/2014 - 10/8.5 = 8.5");
        auxList.add("4ª avaliação - 25/05/2014 - 6/10 = 6");
        auxList.add("Trabalho III - 25/05/2014 - 4/10 = 3.48");
        listData.put(listGroup.get(2), auxList);

        auxList = new ArrayList<String>();
        auxList.add("3ª avaliação - 25/03/2014 - 10/8.5 = 8.5");
        auxList.add("4ª avaliação - 25/05/2014 - 6/10 = 6");
        auxList.add("Trabalho III - 25/05/2014 - 4/10 = 3.48");
        listData.put(listGroup.get(3), auxList);

    }
}