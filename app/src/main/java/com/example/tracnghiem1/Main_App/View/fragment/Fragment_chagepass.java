package com.example.tracnghiem1.Main_App.View.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tracnghiem1.Login.Model.url_sever;
import com.example.tracnghiem1.Main_App.View.Activity.Mainc_Activity;
import com.example.tracnghiem1.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Fragment_chagepass extends Fragment {
    EditText edt_oldpass,edt_newpass,edt_cfpass,edt_cfcode;
    Button btnsubmit;
    private View view;
    TextView codecheck;
    private String code;
    private String oldpass;
    private String id_update;


    public Fragment_chagepass() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_chagepass, container, false);
        // Inflate the layout for this fragment

//get id and old pass
        id_update = Mainc_Activity.arrbundelget.get(0).getId();
        oldpass = Mainc_Activity.arrbundelget.get(0).getPass();
//        Toast.makeText(getActivity(), id_update, Toast.LENGTH_SHORT).show();
        inits();

        radomcode();
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkvalidate();
            }
        });

        return view;
    }

    private void inits() {
        edt_oldpass = view.findViewById(R.id.edt_oldpass);
        edt_newpass = view.findViewById(R.id.edt_newpass);
        edt_cfpass = view.findViewById(R.id.edt_cfnewpass);
        edt_cfcode = view.findViewById(R.id.edt_cfcode);
        btnsubmit = view.findViewById(R.id.btnchangepass);
        codecheck = view.findViewById(R.id.tv_coderq);
    }

    private void radomcode()
    {

        Random rand = new Random();
        int random  =  rand.nextInt((10000 - 1000) + 1) + 1000;

        code = String.valueOf(random);
        codecheck.setText(code);
    }
    private void checkvalidate()
    {
        String getoldpass = edt_oldpass.getText().toString().trim();
        String getnewpass = edt_newpass.getText().toString().trim();
        String getcfpass = edt_cfpass.getText().toString().trim();
        String getcode = edt_cfcode.getText().toString().trim();
        if (getoldpass.length() == 0 || getnewpass.length() ==0 || getcfpass.length()==0 || getcode.length()==0)
        {
            Toast.makeText(getActivity(), "Vui lòng nhập đủ dữ liệu !", Toast.LENGTH_SHORT).show();
            return;
        }
        if
        (getoldpass.equals(oldpass)  && getnewpass.equals(getcfpass) && getcode.equals(code))
        {
            Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            UpdatePass(url_sever.updatepass);
            return;
        }

        else
        {
            Toast.makeText(getActivity(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
        }
    }

    private void UpdatePass(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success"))
                {
//                    Toast.makeText(getActivity(), "CẬP NHẬT THÀNH CÔNG!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "CẬP NHẬT THẤT BAI", Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("lỗi cập nh" , error.toString());
                        Toast.makeText(getActivity(), "Lỗi hệ thống", Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();

                params.put("passu",edt_newpass.getText().toString().trim());
                params.put("idu",id_update);

                return params;
            }
        };

        requestQueue.add(stringRequest);
        new Mainc_Activity().come_myacc();
    }

}
