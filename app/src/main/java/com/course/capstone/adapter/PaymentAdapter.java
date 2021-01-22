package com.course.capstone.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.course.capstone.R;
import com.course.capstone.models.Child;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentAdapter extends BaseExpandableListAdapter{

    private Context context;
    private List<String> parentData;
    private ArrayList<Child> mChildList;
    private HashMap<String, ArrayList<Child>> mChildHashMap;

    public PaymentAdapter(Context context, List<String> parentData, HashMap<String, ArrayList<Child>> childData){
        this.context = context;
        this.parentData = parentData;
        this.mChildHashMap = childData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.mChildHashMap.get(this.parentData.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        final String childText = (String) getChild(groupPosition,childPosition);
        Child childData = (Child)getChild(groupPosition, childPosition);
        if (convertView == null){
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child, null);
        }
        TextView childname = (TextView)convertView.findViewById(R.id.childtext);
        TextView childamount = (TextView)convertView.findViewById(R.id.childamount);

        childname.setText(childData.getShop());
        Log.d("shop",childData.getShop());
        childamount.setText(Integer.toString(childData.getCost()));
        Log.d("amount",Integer.toString(childData.getCost()));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mChildHashMap.get(this.parentData.get(groupPosition)).size();
    }

    @Override
    public int getGroupCount() {
        return this.parentData.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.parentData.get(groupPosition);
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
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.parent, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.parenttext);
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