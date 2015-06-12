package br.edu.ucpel.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import br.edu.ucpel.R;

/**
 * Created by Miguel Aguiar Barbosa on 12/06/15.
 */
public class FrequenciaAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> listGrupo;
    private HashMap<String, List<String>> listItensGrupo;
    private HashMap<String, List<String>> listItensGrupo2;

    public FrequenciaAdapter(Context context, List<String> listDataHeader,
                            HashMap<String, List<String>> listChildData,
                            HashMap<String, List<String>> listChildData2) {
        this._context = context;
        this.listGrupo = listDataHeader;
        this.listItensGrupo = listChildData;
        this.listItensGrupo2 = listChildData2;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listItensGrupo.get(this.listGrupo.get(groupPosition))
                .get(childPosititon);
    }

    public Object getChild2(int groupPosition, int childPosititon) {
        return this.listItensGrupo2.get(this.listGrupo.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        final String childText2 = (String) getChild2(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_expandable_list_view, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem1);

        TextView txtListChild2 = (TextView) convertView.findViewById(R.id.lblListItem2);

        txtListChild.setText(childText);
        txtListChild2.setText(childText2);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listItensGrupo.get(this.listGrupo.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listGrupo.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listGrupo.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_expandable_list_view, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}