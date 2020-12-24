package com.example.tracnghiem1.Main_App.View.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.tracnghiem1.Login.View.Activity.Login;
import com.example.tracnghiem1.Main_App.View.Activity.Mainc_Activity;
import com.example.tracnghiem1.R;

import java.util.HashMap;
import java.util.Map;

public class Fragment_aboutme extends Fragment {
    private View view;
    TextView txtuser,txtemail,txtphone;
    String user,email,phone,id;
    Button btndelete;



    public Fragment_aboutme() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_aboutme, container, false);
        inits();

        // get Acc
        id = Mainc_Activity.arrbundelget.get(0).getId();
        user = Mainc_Activity.arrbundelget.get(0).getUser();
        email = Mainc_Activity.arrbundelget.get(0).getEmail();
        phone = Mainc_Activity.arrbundelget.get(0).getPhone();
        // setText

        txtuser.setText(user);
        txtemail.setText(email);
        txtphone.setText(phone);


        // btn delete

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogdelete();
            }
        });

        // Inflate the layout for this fragment
        return view ;
    }

    private void inits() {
        txtuser = view.findViewById(R.id.tv_aboutme_user);
        txtemail = view.findViewById(R.id.tv_aboutme_email);
        txtphone = view.findViewById(R.id.tv_aboutme_phone);
        btndelete = view.findViewById(R.id.btndelete);

    }

    // confilm exit
    public void dialogdelete(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),R.style.AlertDialogStyle);
        // builder.setIcon(R.drawable.exit);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa tài khoản này hay không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteacc(url_sever.deleteacc);
                startActivity(new Intent(getActivity(), Login.class));

            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }

    private void deleteacc(String url)
    {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success"))
                {
                    Toast.makeText(getActivity(), "Xóa thành công tài khoản!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Lỗi xóa", Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("lỗi xóa" , error.toString());
                        Toast.makeText(getActivity(), "Lỗi hệ thống", Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();

                params.put("idacc",id.trim());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}
