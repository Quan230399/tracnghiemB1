package com.example.tracnghiem1.Main_App.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tracnghiem1.Main_App.model.sode_kiemtra;
import com.example.tracnghiem1.R;

import java.util.ArrayList;
import java.util.List;

public class kiemtra_grid_adapter extends ArrayAdapter<sode_kiemtra> {
    public kiemtra_grid_adapter(Context context, ArrayList<sode_kiemtra> exam) {
        super(context, 0, exam);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView= inflater.inflate(R.layout.item_gridv_kiemtra, parent, false);
        }

        TextView tvName= (TextView) convertView.findViewById(R.id.tvNumExam);
        ImageView imgIcon= (ImageView) convertView.findViewById(R.id.imgIcon);

        sode_kiemtra p= getItem(position);
        if(p!=null){
            tvName.setText(""+ p.getDe());
            imgIcon.setImageResource(R.drawable.test);
        }

        return convertView;
    }
}
