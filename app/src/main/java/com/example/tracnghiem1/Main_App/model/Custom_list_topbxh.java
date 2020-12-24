package com.example.tracnghiem1.Main_App.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tracnghiem1.R;

import java.util.List;

public class Custom_list_topbxh extends ArrayAdapter<topbxh> {
    public Custom_list_topbxh(@NonNull Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
    public Custom_list_topbxh(Context context, int resource, List<topbxh> items){
        super(context, resource, items);

    }
    public View getView(int position, View view, ViewGroup parent){
        View v = view;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.custom_lisview_top, null);

        }
        topbxh item = getItem(position);
        if (item != null) {
            // set Text View
            TextView tv_name = (TextView) v.findViewById(R.id.tv_item_name);
            TextView tv_email = (TextView) v.findViewById(R.id.tv_item_email);
            TextView tv_score = (TextView) v.findViewById(R.id.tv_item_score);
            // set text
            tv_name.setText(item.getName());
            tv_email.setText(item.getEmail());
            tv_score.setText(item.getScore());


        }
        return v;

    }

}
